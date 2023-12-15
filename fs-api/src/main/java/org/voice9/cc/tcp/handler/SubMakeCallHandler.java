package org.voice9.cc.tcp.handler;

import org.springframework.stereotype.Component;
import org.voice9.cc.tcp.event.SubMakeCallEvent;
import org.voice9.cc.tcp.handler.base.SubBaseHandler;
import org.voice9.cc.configration.HandlerType;

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
