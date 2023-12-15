/*
 * Copyright 2010 david varnes.
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.voice9.cc.fs.esl.inbound;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.voice9.cc.fs.esl.internal.Context;
import org.voice9.cc.fs.esl.internal.IModEslApi;
import org.voice9.cc.fs.esl.transport.CommandResponse;
import org.voice9.cc.fs.esl.transport.SendMsg;
import org.voice9.cc.fs.esl.transport.event.EslEvent;
import org.voice9.cc.fs.esl.transport.message.EslMessage;

import java.net.SocketAddress;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Entry point to connect to a running FreeSWITCH Event Socket Library module, as a client.
 * <p/>
 * This class provides what the FreeSWITCH documentation refers to as an 'Inbound' connection
 * to the Event Socket module. That is, with reference to the socket listening on the FreeSWITCH
 * server, this client occurs as an inbound connection to the server.
 * <p/>
 * See <a href="http://wiki.freeswitch.org/wiki/Mod_event_socket">http://wiki.freeswitch.org/wiki/Mod_event_socket</a>
 */
public class Client implements IModEslApi {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final List<IEslEventListener> eventListeners = new CopyOnWriteArrayList<>();
    private final AtomicBoolean authenticatorResponded = new AtomicBoolean(false);
    private final ConcurrentHashMap<String, CompletableFuture<EslEvent>> backgroundJobs = new ConcurrentHashMap<>();

    private boolean authenticated;
    private CommandResponse authenticationResponse;
    private Optional<Context> clientContext = Optional.empty();

    private Channel channel;

    private EventLoopGroup workerGroup;

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("inbound-pool-%d").build();

    private static ExecutorService callbackExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100000), namedThreadFactory);

    public void addEventListener(IEslEventListener listener) {
        if (listener != null) {
            eventListeners.add(listener);
        }
    }

    @Override
    public boolean canSend() {
        return clientContext.isPresent() && clientContext.get().canSend() && authenticated;
    }

    private void checkConnected() {
        if (!canSend()) {
            throw new IllegalStateException("Not connected to FreeSWITCH Event Socket");
        }
    }


    /**
     * Attempt to establish an authenticated connection to the nominated FreeSWITCH ESL server socket.
     * This call will block, waiting for an authentication handshake to occur, or timeout after the
     * supplied number of seconds.
     *
     * @param clientAddress  a SocketAddress representing the endpoint to connect to
     * @param password       server event socket is expecting (set in event_socket_conf.xml)
     * @param timeoutSeconds number of seconds to wait for the server socket before aborting
     */
    public void connect(SocketAddress clientAddress, String password, int timeoutSeconds) throws Exception {
        // If already connected, disconnect first
        if (canSend()) {
            close();
        }
        workerGroup = new NioEventLoopGroup();

        // Configure this client
        Bootstrap bootstrap = new Bootstrap().group(workerGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true);

        // Add ESL handler and factory
        InboundClientHandler handler = new InboundClientHandler(password, protocolListener);
        bootstrap.handler(new InboundChannelInitializer(handler));

        // Attempt connection
        ChannelFuture future = bootstrap.connect(clientAddress);

        // Wait till attempt succeeds, fails or timeouts
        if (!future.awaitUninterruptibly(timeoutSeconds, TimeUnit.SECONDS)) {
            throw new InboundConnectionException("Timeout connecting to " + clientAddress);
        }
        // Did not timeout
        channel = future.channel();
        // But may have failed anyway
        if (!future.isSuccess()) {
            log.warn("Failed to connect to [{}]", clientAddress, future.cause());

            workerGroup.shutdownGracefully();

            throw new InboundConnectionException("Could not connect to " + clientAddress, future.cause());
        }

        log.info("Connected to {}", clientAddress);

        //  Wait for the authentication handshake to call back
        while (!authenticatorResponded.get()) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                // ignore
            }
        }

        this.clientContext = Optional.of(new Context(channel, handler));

        if (!authenticated) {
            throw new InboundConnectionException("Authentication failed: " + authenticationResponse.getReplyText());
        }

        log.info("Authenticated");
    }

    public Channel getChannel() {
        return channel;
    }

    /**
     * Sends a FreeSWITCH API command to the server and blocks, waiting for an immediate response from the
     * server.
     * <p/>
     * The outcome of the command from the server is retured in an {@link EslMessage} object.
     *
     * @param command API command to send
     * @param arg     command arguments
     * @return an {@link EslMessage} containing command results
     */
    @Override
    public EslMessage sendApiCommand(String command, String arg) {
        checkConnected();
        return clientContext.get().sendApiCommand(command, arg);
    }

    /**
     * Submit a FreeSWITCH API command to the server to be executed in background mode. A synchronous
     * response from the server provides a UUID to identify the job execution results. When the server
     * has completed the job execution it fires a BACKGROUND_JOB Event with the execution results.<p/>
     * Note that this Client must be subscribed in the normal way to BACKGOUND_JOB Events, in order to
     * receive this event.
     *
     * @param command API command to send
     * @param arg     command arguments
     * @return String Job-UUID that the server will tag result event with.
     */
    @Override
    public CompletableFuture<EslEvent> sendBackgroundApiCommand(String command, String arg) {
        checkConnected();
        return clientContext.get().sendBackgroundApiCommand(command, arg);
    }

    /**
     * Set the current event subscription for this connection to the server.  Examples of the events
     * argument are:
     * <pre>
     *   ALL
     *   CHANNEL_CREATE CHANNEL_DESTROY HEARTBEAT
     *   CUSTOM conference::maintenance
     *   CHANNEL_CREATE CHANNEL_DESTROY CUSTOM conference::maintenance sofia::register sofia::expire
     * </pre>
     * Subsequent calls to this method replaces any previous subscriptions that were set.
     * </p>
     * Note: current implementation can only process 'plain' events.
     *
     * @param format can be { plain | xml }
     * @param events { all | space separated list of events }
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse setEventSubscriptions(EventFormat format, String events) {
        checkConnected();
        return clientContext.get().setEventSubscriptions(format, events);
    }

    /**
     * Cancel any existing event subscription.
     *
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse cancelEventSubscriptions() {
        checkConnected();
        return clientContext.get().cancelEventSubscriptions();
    }

    /**
     * Add an event filter to the current set of event filters on this connection. Any of the event headers
     * can be used as a filter.
     * </p>
     * Note that event filters follow 'filter-in' semantics. That is, when a filter is applied
     * only the filtered values will be received. Multiple filters can be added to the current
     * connection.
     * </p>
     * Example filters:
     * <pre>
     *    eventHeader        valueToFilter
     *    ----------------------------------
     *    Event-Name         CHANNEL_EXECUTE
     *    Channel-State      CS_NEW
     * </pre>
     *
     * @param eventHeader   to filter on
     * @param valueToFilter the value to match
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse addEventFilter(String eventHeader, String valueToFilter) {
        checkConnected();
        return clientContext.get().addEventFilter(eventHeader, valueToFilter);
    }

    /**
     * Delete an event filter from the current set of event filters on this connection.  See
     *
     * @param eventHeader   to remove
     * @param valueToFilter to remove
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse deleteEventFilter(String eventHeader, String valueToFilter) {
        checkConnected();
        return clientContext.get().deleteEventFilter(eventHeader, valueToFilter);
    }


    /**
     * Send a {@link SendMsg} command to FreeSWITCH.  This client requires that the {@link SendMsg}
     * has a call UUID parameter.
     *
     * @param sendMsg a {@link SendMsg} with call UUID
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse sendMessage(SendMsg sendMsg) {
        checkConnected();
        return clientContext.get().sendMessage(sendMsg);
    }

    /**
     * Enable log output.
     *
     * @param level using the same values as in console.conf
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse setLoggingLevel(LoggingLevel level) {
        checkConnected();
        return clientContext.get().setLoggingLevel(level);
    }

    /**
     * Disable any logging previously enabled with setLogLevel().
     *
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse cancelLogging() {
        checkConnected();
        return clientContext.get().cancelLogging();
    }

    @Override
    public boolean isActivate() {
        if (clientContext == null || !clientContext.isPresent()) {
            return false;
        }
        return clientContext.get().isActivate();
    }

    /**
     * Close the socket connection
     *
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public void close() {
        if (!isActivate()) {
            return;
        }
        if (clientContext.isPresent()) {
            clientContext.get().sendCommand("exit");
        }
        try {
            channel.close();
            workerGroup.shutdownGracefully();
            callbackExecutor.shutdown();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /*
     *  Internal observer of the ESL protocol
     */
    private final IEslProtocolListener protocolListener = new IEslProtocolListener() {

        @Override
        public void authResponseReceived(CommandResponse response) {
            authenticatorResponded.set(true);
            authenticated = response.isOk();
            authenticationResponse = response;
            log.debug("Auth response success={}, message=[{}]", authenticated, response.getReplyText());
        }

        @Override
        public void eventReceived(final Context ctx, final EslEvent event) {
            for (final IEslEventListener listener : eventListeners) {
                callbackExecutor.execute(() -> listener.onEslEvent(ctx, event));
            }
        }

        @Override
        public void disconnected(Channel channel) {
            log.debug("chanDisconnected ...");
            for (final IEslEventListener listener : eventListeners) {
                callbackExecutor.execute(() -> listener.onClose(channel));
            }
        }
    };
}
