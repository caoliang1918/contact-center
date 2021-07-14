package org.zhongweixian.cc.tcp.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.tcp.event.SubStartCallEvent;
import org.zhongweixian.cc.tcp.handler.base.SubBaseHandler;

/**
 * Create by caoliang on 2020/11/3
 */
@Component
@HandlerType("SUB_START_CALL")
public class SubStartCallHandler extends SubBaseHandler<SubStartCallEvent> {
    @Override
    public void handleEvent(SubStartCallEvent event) {

    }
}
