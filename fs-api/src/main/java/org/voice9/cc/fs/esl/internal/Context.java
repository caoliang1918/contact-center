package org.voice9.cc.fs.esl.internal;

import io.netty.channel.Channel;
import org.voice9.cc.fs.esl.transport.message.EslMessage;
import org.voice9.cc.fs.esl.transport.CommandResponse;
import org.voice9.cc.fs.esl.transport.SendMsg;
import org.voice9.cc.fs.esl.transport.event.EslEvent;

import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Throwables.throwIfUnchecked;
import static com.google.common.util.concurrent.Futures.getUnchecked;


public class Context implements IModEslApi {

    private final AbstractEslClientHandler handler;
    private final Channel channel;

    public Context(Channel channel, AbstractEslClientHandler clientHandler) {
        this.handler = clientHandler;
        this.channel = channel;
    }

    @Override
    public boolean canSend() {
        return channel != null && channel.isActive();
    }

    /**
     * Sends a mod_event_socket command to FreeSWITCH server and blocks, waiting for an immediate response from the
     * server.
     * <p/>
     * The outcome of the command from the server is returned in an {@link EslMessage} object.
     *
     * @param command a mod_event_socket command to send
     * @return an {@link EslMessage} containing command results
     */
    public EslMessage sendCommand(String command) {

        checkArgument(!isNullOrEmpty(command), "command cannot be null or empty");

        try {

            return getUnchecked(handler.sendApiSingleLineCommand(channel, command.toLowerCase().trim()));

        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }
    }

    /**
     * Sends a FreeSWITCH API command to the server and blocks, waiting for an immediate response from the
     * server.
     * <p/>
     * The outcome of the command from the server is returned in an {@link EslMessage} object.
     *
     * @param command API command to send
     * @param arg     command arguments
     * @return an {@link EslMessage} containing command results
     */
    @Override
    public EslMessage sendApiCommand(String command, String arg) {

        checkArgument(!isNullOrEmpty(command), "command cannot be null or empty");

        try {

            final StringBuilder sb = new StringBuilder();
            sb.append("api ").append(command);
            if (!isNullOrEmpty(arg)) {
                sb.append(' ').append(arg);
            }

            return getUnchecked(handler.sendApiSingleLineCommand(channel, sb.toString()));

        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }
    }

    /**
     * Submit a FreeSWITCH API command to the server to be executed in background mode. A synchronous
     * response from the server provides a UUID to identify the job execution results. When the server
     * has completed the job execution it fires a BACKGROUND_JOB Event with the execution results.<p/>
     * Note that this Client must be subscribed in the normal way to BACKGROUND_JOB Events, in order to
     * receive this event.
     *
     * @param command API command to send
     * @param arg     command arguments
     * @return String Job-UUID that the server will tag result event with.
     */
    @Override
    public CompletableFuture<EslEvent> sendBackgroundApiCommand(String command, String arg) {

        checkArgument(!isNullOrEmpty(command), "command cannot be null or empty");

        final StringBuilder sb = new StringBuilder();
        sb.append("bgapi ").append(command);
        if (!isNullOrEmpty(arg)) {
            sb.append(' ').append(arg);
        }

        return handler.sendBackgroundApiCommand(channel, sb.toString());
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

        // temporary hack
        checkState(format.equals(EventFormat.PLAIN), "Only 'plain' event format is supported at present");

        try {

            final StringBuilder sb = new StringBuilder();
            sb.append("event ").append(format.toString());
            if (!isNullOrEmpty(events)) {
                sb.append(' ').append(events);
            }

            final EslMessage response = getUnchecked(handler.sendApiSingleLineCommand(channel, sb.toString()));
            return new CommandResponse(sb.toString(), response);

        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }

    }

    /**
     * Cancel any existing event subscription.
     *
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse cancelEventSubscriptions() {

        try {
            final EslMessage response = getUnchecked(handler.sendApiSingleLineCommand(channel, "noevents"));
            return new CommandResponse("noevents", response);
        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }
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

        checkArgument(!isNullOrEmpty(eventHeader), "eventHeader cannot be null or empty");

        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("filter ").append(eventHeader);
            if (!isNullOrEmpty(valueToFilter)) {
                sb.append(' ').append(valueToFilter);
            }

            final EslMessage response = getUnchecked(handler.sendApiSingleLineCommand(channel, sb.toString()));
            return new CommandResponse(sb.toString(), response);

        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }
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

        checkArgument(!isNullOrEmpty(eventHeader), "eventHeader cannot be null or empty");

        try {

            final StringBuilder sb = new StringBuilder();
            sb.append("filter delete ").append(eventHeader);
            if (!isNullOrEmpty(valueToFilter)) {
                sb.append(' ').append(valueToFilter);
            }

            final EslMessage response = getUnchecked(handler.sendApiSingleLineCommand(channel, sb.toString()));
            return new CommandResponse(sb.toString(), response);

        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }
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

        checkNotNull(sendMsg, "sendMsg cannot be null");

        try {
            final EslMessage response = getUnchecked(handler.sendApiMultiLineCommand(channel, sendMsg.getMsgLines()));
            return new CommandResponse(sendMsg.toString(), response);
        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }

    }

    /**
     * Enable log output.
     *
     * @param level using the same values as in console.conf
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse setLoggingLevel(LoggingLevel level) {

        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("log ").append(level.toString());

            final EslMessage response = getUnchecked(handler.sendApiSingleLineCommand(channel, sb.toString()));
            return new CommandResponse(sb.toString(), response);
        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }

    }

    /**
     * Disable any logging previously enabled with setLogLevel().
     *
     * @return a {@link CommandResponse} with the server's response.
     */
    @Override
    public CommandResponse cancelLogging() {

        try {
            final EslMessage response = getUnchecked(handler.sendApiSingleLineCommand(channel, "nolog"));
            return new CommandResponse("nolog", response);
        } catch (Throwable t) {
            throwIfUnchecked(t);
            throw t;
        }
    }

    @Override
    public boolean isActivate() {
        return channel != null && channel.isActive();
    }

    @Override
    public void close() {
        try {
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
        } catch (Exception e) {
        }
    }
}
