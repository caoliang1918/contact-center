package org.zhongweixian.cc.tcp.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.tcp.event.SubStopCallEvent;
import org.zhongweixian.cc.tcp.handler.base.SubBaseHandler;

/**
 * Create by caoliang on 2020/10/30
 */

@Component
@HandlerType("SUB_STOP_CALL")
public class SubStopCallHandler extends SubBaseHandler<SubStopCallEvent> {
    @Override
    public void handleEvent(SubStopCallEvent event) {

    }
}
