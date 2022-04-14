package org.zhongweixian.cc.fs.esl.outbound;

import io.netty.channel.Channel;
import org.zhongweixian.cc.fs.esl.transport.event.EslEvent;
import org.zhongweixian.cc.fs.esl.inbound.IEslEventListener;
import org.zhongweixian.cc.fs.esl.internal.Context;

public interface IClientHandler extends IEslEventListener {
    void onConnect(Context ctx, EslEvent event);

    void onClose(Channel channel);
}
