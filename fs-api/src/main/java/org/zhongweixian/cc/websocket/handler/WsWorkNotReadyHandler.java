package org.zhongweixian.cc.websocket.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsWorkNotReadyEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

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
