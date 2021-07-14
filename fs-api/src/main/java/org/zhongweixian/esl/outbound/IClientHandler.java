package org.zhongweixian.esl.outbound;

import org.zhongweixian.esl.inbound.IEslEventListener;
import org.zhongweixian.esl.internal.Context;
import org.zhongweixian.esl.transport.event.EslEvent;

public interface IClientHandler extends IEslEventListener {
    void onConnect(Context ctx, EslEvent event);
}
