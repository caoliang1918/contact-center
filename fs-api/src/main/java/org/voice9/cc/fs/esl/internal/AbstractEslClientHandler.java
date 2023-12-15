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
package org.voice9.cc.fs.esl.internal;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.voice9.cc.fs.esl.transport.message.EslHeaders;
import org.voice9.cc.fs.esl.transport.message.EslMessage;
import org.voice9.cc.fs.esl.transport.event.EslEvent;
import org.voice9.cc.fs.esl.transport.event.EslEventHeaderNames;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Specialised {@link SimpleChannelInboundHandler} that implements the logic of an ESL connection that
 * is common to both inbound and outbound clients. This
 * handler expects to receive decoded {@link EslMessage} or {@link EslEvent} objects. The key
 * responsibilities for this class are:
 * <ul><li>
 * To synthesise a synchronous command/response api.  All IO operations using the underlying Netty
 * library are intrinsically asynchronous which provides for excellent response and scalability.  This
 * class provides for a blocking wait mechanism for responses to commands issued to the server.  A
 * key assumption here is that the FreeSWITCH server will process synchronous requests in the order they
 * are received.
 * </li><li>
 * Concrete sub classes are expected to 'terminate' the Netty IO processing pipeline (ie be the 'last'
 * handler).
 * </li></ul>
 * Note: implementation requirement is that an {@link ExecutionHandler} is placed in the processing
 * pipeline prior to this handler. This will ensure that each incoming message is processed in its
 * own thread (although still guaranteed to be processed in the order of receipt).
 */
public abstract class AbstractEslClientHandler extends SimpleChannelInboundHandler<EslMessage> {
    private final Logger logger = LoggerFactory.getLogger(AbstractEslClientHandler.class);

    public static final String MESSAGE_TERMINATOR = "\n\n";
    public static final String LINE_TERMINATOR = "\n";

    // used to preserve association between adding future to queue and sending message on channel
    private final ReentrantLock syncLock = new ReentrantLock();
    private final ConcurrentLinkedQueue<CompletableFuture<EslMessage>> apiCalls = new ConcurrentLinkedQueue<>();

    private final ConcurrentHashMap<String, CompletableFuture<EslEvent>> backgroundJobs = new ConcurrentHashMap<>();
    private final ExecutorService backgroundJobExecutor = Executors.newCachedThreadPool();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {

        for (final CompletableFuture<EslMessage> apiCall : apiCalls) {
            apiCall.completeExceptionally(e.getCause());
        }

        for (final CompletableFuture<EslEvent> backgroundJob : backgroundJobs.values()) {
            backgroundJob.completeExceptionally(e.getCause());
        }

        ctx.close();

        ctx.fireExceptionCaught(e);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, EslMessage message) throws Exception {
        final String contentType = message.getContentType();
        if (contentType.equals(EslHeaders.Value.TEXT_EVENT_PLAIN) || contentType.equals(EslHeaders.Value.TEXT_EVENT_XML)) {
            //  transform into an event
            final EslEvent eslEvent = new EslEvent(message);
            if ("BACKGROUND_JOB".equals(eslEvent.getEventName())) {
                final String backgroundUuid = eslEvent.getEventHeaders().get(EslEventHeaderNames.JOB_UUID);
                final CompletableFuture<EslEvent> future = backgroundJobs.remove(backgroundUuid);
                if (null != future) {
                    future.complete(eslEvent);
                }
            } else {
                handleEslEvent(ctx, eslEvent);
            }
        } else {
            handleEslMessage(ctx, message);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelInactive();
        backgroundJobExecutor.shutdown();
        handleDisconnectionNotice(ctx);
    }

    protected void handleEslMessage(ChannelHandlerContext ctx, EslMessage message) {
        logger.debug("Received message: [{}]", message);
        final String contentType = message.getContentType();

        switch (contentType) {
            case EslHeaders.Value.API_RESPONSE:
                logger.debug("Api response received [{}]", message);
                apiCalls.poll().complete(message);
                break;

            case EslHeaders.Value.COMMAND_REPLY:
                logger.debug("Command reply received [{}]", message);
                apiCalls.poll().complete(message);
                break;

            case EslHeaders.Value.AUTH_REQUEST:
                logger.debug("Auth request received [{}]", message);
                handleAuthRequest(ctx);
                break;

            case EslHeaders.Value.TEXT_DISCONNECT_NOTICE:
                logger.info("Disconnect notice received [{}]", message);
                handleDisconnectionNotice(ctx);
                break;

            case EslHeaders.Value.REJECTION:
                logger.warn("rejection notice received [{}]", message);
                handleDisconnectionNotice(ctx);
                break;

            default:
                logger.warn("Unexpected message content type [{}]", contentType);
                break;
        }
    }

    /**
     * Synthesise a synchronous command/response by creating a callback object which is placed in
     * queue and blocks waiting for another IO thread to process an incoming {@link EslMessage} and
     * attach it to the callback.
     *
     * @param channel
     * @param command single string to send
     * @return the {@link EslMessage} attached to this command's callback
     */
    public CompletableFuture<EslMessage> sendApiSingleLineCommand(Channel channel, final String command) {
        final CompletableFuture<EslMessage> future = new CompletableFuture<>();
        syncLock.lock();
        try {
            apiCalls.add(future);
            channel.writeAndFlush(command + MESSAGE_TERMINATOR);
        } finally {
            syncLock.unlock();
        }

        return future;
    }

    /**
     * Sends a FreeSWITCH API command to the channel and blocks, waiting for an immediate response from the
     * server.
     * <p/>
     * The outcome of the command from the server is returned in an {@link EslMessage} object.
     *
     * @param channel
     * @param command API command to send
     * @param arg     command arguments
     * @return an {@link EslMessage} containing command results
     */
    public CompletableFuture<EslMessage> sendSyncApiCommand(Channel channel, String command, String arg) {

        checkArgument(!isNullOrEmpty(command), "command may not be null or empty");
        checkArgument(!isNullOrEmpty(arg), "arg may not be null or empty");

        return sendApiSingleLineCommand(channel, "api " + command + ' ' + arg);
    }

    /**
     * Synthesise a synchronous command/response by creating a callback object which is placed in
     * queue and blocks waiting for another IO thread to process an incoming {@link EslMessage} and
     * attach it to the callback.
     *
     * @param channel
     * @return the {@link EslMessage} attached to this command's callback
     */
    public CompletableFuture<EslMessage> sendApiMultiLineCommand(Channel channel, final List<String> commandLines) {
        //  Build command with double line terminator at the end
        final StringBuilder sb = new StringBuilder();
        for (final String line : commandLines) {
            sb.append(line);
            sb.append(LINE_TERMINATOR);
        }
        sb.append(LINE_TERMINATOR);
        logger.info("send:{} message:{}", channel.remoteAddress(), sb.toString());
        final CompletableFuture<EslMessage> future = new CompletableFuture<>();
        syncLock.lock();
        try {
            apiCalls.add(future);
            channel.write(sb.toString());
            channel.flush();
        } finally {
            syncLock.unlock();
        }

        return future;

    }

    /**
     * Returns the Job UUID of that the response event will have.
     *
     * @param channel
     * @param command
     * @return Job-UUID as a string
     */
    public CompletableFuture<EslEvent> sendBackgroundApiCommand(Channel channel, final String command) {
        logger.info("send:{} message:{}", channel.remoteAddress(), command);
        return sendApiSingleLineCommand(channel, command).thenComposeAsync(result -> {
            if (result.hasHeader(EslHeaders.Name.JOB_UUID)) {
                final String jobId = result.getHeaderValue(EslHeaders.Name.JOB_UUID);
                final CompletableFuture<EslEvent> resultFuture = new CompletableFuture<>();
                backgroundJobs.put(jobId, resultFuture);
                return resultFuture;
            } else {
                final CompletableFuture<EslEvent> resultFuture = new CompletableFuture<>();
                resultFuture.completeExceptionally(new IllegalStateException("Missing Job-UUID header in bgapi response"));
                return resultFuture;
            }
        }, backgroundJobExecutor);
    }

    /**
     * @param ctx
     * @param event
     */
    protected abstract void handleEslEvent(ChannelHandlerContext ctx, EslEvent event);

    /**
     * @param ctx
     */
    protected abstract void handleAuthRequest(ChannelHandlerContext ctx);

    /**
     *
     */
    protected abstract void handleDisconnectionNotice(ChannelHandlerContext ctx);

}
