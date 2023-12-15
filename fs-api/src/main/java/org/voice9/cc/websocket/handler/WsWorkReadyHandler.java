package org.voice9.cc.websocket.handler;

import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.websocket.handler.base.WsBaseHandler;
import org.voice9.cc.websocket.event.WsWorkReadyEvent;

/**
 * Create by caoliang on 2020/11/1
 * <p>
 * 上班之后的状态
 */
@Component
@HandlerType("WS_WORK_READY")
public class WsWorkReadyHandler extends WsBaseHandler<WsWorkReadyEvent> {
    @Override
    public void handleEvent(WsWorkReadyEvent event) {
        logger.info("{}", event.toString());
    }
}
