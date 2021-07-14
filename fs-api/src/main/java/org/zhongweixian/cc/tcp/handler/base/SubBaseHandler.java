package org.zhongweixian.cc.tcp.handler.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhongweixian.cc.configration.Handler;
import org.zhongweixian.cc.tcp.event.base.SubBaseEvent;
import org.zhongweixian.cc.websocket.WebSocketHandler;

/**
 * Create by caoliang on 2020/10/30
 */
public abstract class SubBaseHandler<T extends SubBaseEvent> implements Handler<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected WebSocketHandler webSocketHandler;

    protected void sendAgentMessage(String agentKey, String payload) {
        webSocketHandler.sendMessgae(agentKey, payload);
    }
}
