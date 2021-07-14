package org.zhongweixian.cc.websocket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsMonitorCancelEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

/**
 * Create by caoliang on 2020/11/10
 */
@Component
@HandlerType("WS_MONITOR_CANCEL")
public class WsMonitorCancelHandler extends WsBaseHandler<WsMonitorCancelEvent> {

    @Autowired
    private WsMonitorHandler monitorHandler;

    @Override
    public void handleEvent(WsMonitorCancelEvent event) {
        logger.info("agent cancel monitor", event.getAgentKey());
        monitorHandler.monitorCancel(event.getAgentKey());
    }
}
