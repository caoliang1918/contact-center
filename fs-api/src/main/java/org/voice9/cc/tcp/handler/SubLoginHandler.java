package org.voice9.cc.tcp.handler;

import org.springframework.stereotype.Component;
import org.voice9.cc.tcp.handler.base.SubBaseHandler;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.tcp.event.SubLoginEvent;

/**
 * Created by caoliang on 2021/8/8.
 */
@HandlerType("SUB_LOGIN")
@Component
public class SubLoginHandler extends SubBaseHandler<SubLoginEvent> {
    @Override
    public void handleEvent(SubLoginEvent event) {

    }
}