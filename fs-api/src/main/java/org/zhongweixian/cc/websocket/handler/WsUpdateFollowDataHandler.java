package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsUpdateFollowDataEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoliang on 2021/7/19
 * <p>
 * 更新随路数据
 */
@Component
@HandlerType("WS_UPDATE_FOLLOWDATA")
public class WsUpdateFollowDataHandler extends WsBaseHandler<WsUpdateFollowDataEvent> {
    @Override
    public void handleEvent(WsUpdateFollowDataEvent event) {
        AgentInfo agentInfo = getAgent(event);
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (callInfo == null) {
            return;
        }
        Map<String, Object> followData = callInfo.getFollowData();
        if (followData == null) {
            followData = new HashMap<>();
        }
        followData.putAll(event.getFollowData());
    }
}
