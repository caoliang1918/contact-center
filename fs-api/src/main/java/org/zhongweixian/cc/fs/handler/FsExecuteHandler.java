package org.zhongweixian.cc.fs.handler;

import org.cti.cc.po.CallInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsExecuteEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

/**
 * Created by caoliang on 2020/8/31
 */
@Component
@HandlerType("CHANNEL_EXECUTE")
public class FsExecuteHandler extends BaseEventHandler<FsExecuteEvent> {
    private Logger logger = LoggerFactory.getLogger(FsExecuteHandler.class);

    @Override
    public void handleEvent(FsExecuteEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
    }
}
