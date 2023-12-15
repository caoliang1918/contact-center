package org.voice9.cc.fs.handler;

import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.handler.base.BaseEventHandler;
import org.voice9.cc.fs.event.FsOutgoingEvent;

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
