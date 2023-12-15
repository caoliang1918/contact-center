package org.voice9.cc.websocket.handler;

import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.websocket.handler.base.WsBaseHandler;
import org.voice9.cc.websocket.event.WsWorkNotReadyEvent;

/**
 * Create by caoliang on 2020/11/1
 * <p>
 * 自定义忙碌状态
 */
@Component
@HandlerType("WS_WORK_NOT_READY")
public class WsWorkNotReadyHandler extends WsBaseHandler<WsWorkNotReadyEvent> {
    @Override
    public void handleEvent(WsWorkNotReadyEvent event) {

    }
}
