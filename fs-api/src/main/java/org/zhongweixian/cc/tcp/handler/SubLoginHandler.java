package org.zhongweixian.cc.tcp.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.tcp.event.SubLoginEvent;
import org.zhongweixian.cc.tcp.handler.base.SubBaseHandler;

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