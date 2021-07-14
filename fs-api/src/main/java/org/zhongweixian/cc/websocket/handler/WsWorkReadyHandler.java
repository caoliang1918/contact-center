package org.zhongweixian.cc.websocket.handler;

import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsWorkReadyEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

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
