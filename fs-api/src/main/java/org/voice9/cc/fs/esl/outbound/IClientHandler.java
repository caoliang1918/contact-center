package org.voice9.cc.fs.esl.outbound;

import io.netty.channel.Channel;
import org.voice9.cc.fs.esl.transport.event.EslEvent;
import org.voice9.cc.fs.esl.inbound.IEslEventListener;
import org.voice9.cc.fs.esl.internal.Context;

public interface IClientHandler extends IEslEventListener {
    void onConnect(Context ctx, EslEvent event);

    void onClose(Channel channel);
}
