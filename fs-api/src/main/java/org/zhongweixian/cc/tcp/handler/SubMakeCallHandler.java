package org.zhongweixian.cc.tcp.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.tcp.event.SubMakeCallEvent;
import org.zhongweixian.cc.tcp.handler.base.SubBaseHandler;

/**
 * Create by caoliang on 2020/10/30
 */
@Component
@HandlerType("SUB_MAKE_CALL")
public class SubMakeCallHandler extends SubBaseHandler<SubMakeCallEvent> {

    @Override
    public void handleEvent(SubMakeCallEvent event) {

    }
}
