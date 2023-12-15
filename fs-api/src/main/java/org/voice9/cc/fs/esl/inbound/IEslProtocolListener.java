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
import org.voice9.cc.fs.esl.transport.CommandResponse;
import org.voice9.cc.fs.esl.transport.event.EslEvent;

/**
 * End users of the {@link Client} should not need to use this class.
 * <p/>
 * Allow client implementations to observe events arriving from the server.
 */
interface IEslProtocolListener {
	void authResponseReceived(CommandResponse response);

	void eventReceived(Context ctx, EslEvent event);

	void disconnected(Channel channel);
}
