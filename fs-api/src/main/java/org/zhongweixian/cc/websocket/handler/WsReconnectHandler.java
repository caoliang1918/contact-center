package org.zhongweixian.cc.websocket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CallInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.websocket.WebSocketHandler;
import org.zhongweixian.cc.websocket.event.WsReconnectEvent;
import org.zhongweixian.cc.websocket.event.base.ChannelEntity;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsCallEntity;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

/**
 * Created by caoliang on 2022/2/10
 */

@Component
@HandlerType("WS_RECONNECT")
public class WsReconnectHandler extends WsBaseHandler<WsReconnectEvent> {

    @Autowired
    private AgentService agentService;

    @Autowired
    private WebSocketHandler webSocketHandler;


    @Override
    public void handleEvent(WsReconnectEvent event) {
        logger.info("{}", event);
        AgentInfo agentInfo = cacheService.getAgentInfo(event.getAgentKey());
        /**
         * 先判断，坐席是否存在重复登录
         */
        ChannelEntity entity = webSocketHandler.getChanel(event.getChannel().id());
        Channel channel = webSocketHandler.getChannel(entity.getClient());
        if (channel != null && channel.isOpen()) {
            /**
             * 现将老的channel关闭
             */
            ChannelEntity exist = webSocketHandler.getChanel(channel.id());
            if (exist != null && exist.getChannel().isOpen()) {
                try {
                    /**
                     * 授权通过
                     */
                    entity = webSocketHandler.getChanel(event.getChannel().id());
                    entity.setAuthorization(true);
                    entity.setClient(event.getAgentKey());
                    webSocketHandler.addAgentChannel(agentInfo.getAgentKey(), entity.getChannel());

                    String payload = JSON.toJSONString(new WsResponseEntity<>(ErrorCode.AGENT_LOGIN_IN_OTHER, event.getCmd(), event.getAgentKey()));
                    logger.warn("send agent:{} message:{}", exist.getClient(), payload);
                    exist.getChannel().writeAndFlush(new TextWebSocketFrame(payload));
                    exist.getChannel().close();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        webSocketHandler.addAgentChannel(agentInfo.getAgentKey(), entity.getChannel());


        if (event.getCallId() != null) {
            //返回通话中状态
            CallInfo callInfo = cacheService.getCallInfo(event.getCallId());
            if (callInfo != null) {
                WsCallEntity ringEntity = new WsCallEntity();
                ringEntity.setCallId(callInfo.getCallId());
                ringEntity.setCallType(callInfo.getCallType());
                ringEntity.setAgentState(AgentState.TALKING);
                ringEntity.setCaller(callInfo.getCaller());
                ringEntity.setCalled(callInfo.getCalled());
                sendMessage(event, new WsResponseEntity<WsCallEntity>(AgentState.TALKING.name(), event.getAgentKey(), ringEntity));
                return;
            }
            agentInfo.setCallId(null);
        }
        


    }
}
