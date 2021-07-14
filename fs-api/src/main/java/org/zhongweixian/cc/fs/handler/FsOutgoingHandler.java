package org.zhongweixian.cc.fs.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;
import org.zhongweixian.cc.fs.event.FsOutgoingEvent;

/**
 * Created by caoliang on 2020/8/29
 * <p>
 * 到达媒体事件
 */
@HandlerType("CHANNEL_OUTGOING")
@Component
public class FsOutgoingHandler extends BaseEventHandler<FsOutgoingEvent> {

    @Override
    public void handleEvent(FsOutgoingEvent event) {

    }


}
