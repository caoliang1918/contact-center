package org.voice9.cc.tcp.handler.base;

import com.voice9.core.po.AgentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.voice9.cc.tcp.event.base.SubBaseEvent;
import org.voice9.cc.configration.Handler;
import org.voice9.cc.websocket.WebSocketHandler;

/**
 * Create by caoliang on 2020/10/30
 */
public abstract class SubBaseHandler<T extends SubBaseEvent> implements Handler<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected WebSocketHandler webSocketHandler;

    protected void sendAgentMessage(AgentInfo agentInfo, String payload) {
        webSocketHandler.sendMessgae(agentInfo,payload);
    }
}
