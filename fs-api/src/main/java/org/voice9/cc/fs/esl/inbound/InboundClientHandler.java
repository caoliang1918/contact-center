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

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.voice9.cc.fs.esl.transport.message.EslHeaders;
import org.voice9.cc.fs.esl.internal.AbstractEslClientHandler;
import org.voice9.cc.fs.esl.internal.Context;
import org.voice9.cc.fs.esl.transport.CommandResponse;
import org.voice9.cc.fs.esl.transport.event.EslEvent;

/**
 * End users of the inbound {@link Client} should not need to use this class.
 * <p/>
 * Specialised {@link AbstractEslClientHandler} that implements the connection logic for an
 * 'Inbound' FreeSWITCH Event Socket connection.  The responsibilities for this class are:
 * <ul><li>
 * To handle the auth request that the FreeSWITCH server will send immediately following a new
 * connection when mode is Inbound.
 * <li>
 * To signal the observing {@link IEslProtocolListener} (expected to be the Inbound client
 * implementation) when ESL events are received.
 * </ul>
 * Note: implementation requirement is that an {@link ExecutionHandler} is placed in the processing
 * pipeline prior to this handler. This will ensure that each incoming message is processed in its
 * own thread (although still guaranteed to be processed in the order of receipt).
 */
public class InboundClientHandler extends AbstractEslClientHandler {
    private Logger logger = LoggerFactory.getLogger(InboundClientHandler.class);

    private final String password;
    private final IEslProtocolListener listener;

    public InboundClientHandler(String password, IEslProtocolListener listener) {
        this.password = password;
        this.listener = listener;
    }

    @Override
    protected void handleEslEvent(ChannelHandlerContext ctx, EslEvent event) {
        listener.eventReceived(new Context(ctx.channel(), this), event);
    }

    @Override
    protected void handleAuthRequest(ChannelHandlerContext ctx) {
        logger.debug("auth requested, sending [auth {}]", "*****");

        sendApiSingleLineCommand(ctx.channel(), "auth " + password)
                .thenAccept(response -> {
                    logger.debug("auth response [{}]", response);
                    if (response.getContentType().equals(EslHeaders.Value.COMMAND_REPLY)) {
                        final CommandResponse commandResponse = new CommandResponse("auth " + password, response);
                        listener.authResponseReceived(commandResponse);
                    } else {
                        logger.error("bad auth response message [{}]", response);
                        throw new IllegalStateException("incorrect auth response");
                    }
                });
    }

    @Override
    protected void handleDisconnectionNotice(ChannelHandlerContext ctx) {
        listener.disconnected(ctx.channel());
    }

}
