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

import io.netty.channel.Channel;
import org.voice9.cc.fs.esl.internal.Context;
import org.voice9.cc.fs.esl.transport.event.EslEvent;

/**
 * Interface for observers wanting to be notified of incoming FreeSWITCH Event Socket events.
 * <p/>
 * Events are guaranteed to be processed (and listeners notified) in the order in which the
 * events are received off the wire.
 * <p/>
 * This design ensures that incoming event processing is not blocked by any long-running listener process.
 * However multiple listeners will be notified sequentially, and so one slow listener can cause latency
 * to other listeners.
 */
public interface IEslEventListener {
    /**
     * Signal of a server initiated event.
     *
     * @param event as an {@link EslEvent}
     */
    void onEslEvent(Context ctx, EslEvent event);

    void onClose(Channel channel);

}
