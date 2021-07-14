package org.zhongweixian.cc.fs.handler;

import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsHangupCompleteEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by caoliang on 2020/8/23
 */
@Component
@HandlerType("CHANNEL_HANGUP_COMPLETE")
public class FsHangupCompleteHandler extends BaseEventHandler<FsHangupCompleteEvent> {
    @Override
    public void handleEvent(FsHangupCompleteEvent event) {

    }
}
