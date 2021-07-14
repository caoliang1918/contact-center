package org.zhongweixian.cc.websocket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.Agent;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.util.BcryptUtil;
import org.zhongweixian.cc.websocket.WebSocketHandler;
import org.zhongweixian.cc.websocket.event.WsLoginEvnet;
import org.zhongweixian.cc.websocket.event.base.ChannelEntity;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.AgentStateResppnse;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Created by caoliang on 2020/10/29
 * <p>
 * 坐席登录
 * <p>
 * 1001 账号或密码错误
 * 1002 坐席被禁用
 * 1003 技能组为空
 * 1004 坐席通话中，不允许再次登录
 * 1005 坐席在别处登录
 * 1006 坐席重复登录
 */
@Component
@HandlerType("WS_LOGIN")
public class WsLoginHandler extends WsBaseHandler<WsLoginEvnet> {

    @Value("${server.address}:${server.port}")
    private String host;

    @Autowired
    private AgentService agentService;

    @Autowired
    private WebSocketHandler webSocketHandler;


    @Override
    public void handleEvent(WsLoginEvnet event) {
        logger.info("{}", event.toString());
        Channel channel = webSocketHandler.getChannel(event.getAgentKey());
        if (channel != null && channel.isOpen() && channel.id().equals(event.getChannel().id())) {
            //重复登录成功
            logger.warn("{}:坐席重复登录 ", event.getAgentKey());
            sendMessgae(event, new WsResponseEntity<String>(ErrorCode.AGENT_REPEAT_LOGIN, event.getCmd(), event.getAgentKey()));
            return;
        }

        AgentInfo agentInfo = agentService.getAgentInfo(event.getAgentKey());
        if (agentInfo == null) {
            logger.info("agent:{} not exist ", event.getAgentKey());
            sendMessgae(event, new WsResponseEntity<String>(ErrorCode.ACCOUNT_ERROR, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }
        agentInfo.setLoginType(event.getLoginType());
        agentInfo.setWorkType(event.getWorkType());

        //通话中不允许挤掉老的连接
        AgentState state = agentInfo.getAgentState();
        if (state != null && (state.name().contains("CALL") || AgentState.TALKING == state)) {
            sendMessgae(event, new WsResponseEntity<String>(ErrorCode.AGENT_CALLING, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }

        //先判断，坐席是否存在重复登录
        if (channel != null && channel.isOpen()) {
            //现将老的channel关闭
            ChannelEntity exist = webSocketHandler.getChanel(channel.id());
            if (exist != null && exist.getChannel().isOpen()) {
                try {
                    /**
                     * 授权通过
                     */
                    ChannelEntity entity = webSocketHandler.getChanel(event.getChannel().id());
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
        if (!BcryptUtil.checkPwd(event.getPasswd(), agentInfo.getPasswd())) {
            logger.error("agent:{}  password {} is error", event.getAgentKey(), event.getPasswd());
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.ACCOUNT_ERROR, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }

        if (agentInfo.getStatus() == 0) {
            logger.warn("agent:{} 坐席被禁用", event.getAgentKey());
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.ACCOUNT_DISABLED, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }

        if (agentInfo.getGroupId() == null || agentInfo.getGroupId() == 0L) {
            logger.info("agent:{} 技能组为空 , mainGroup is null", event.getAgentKey());
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.AGENT_GROUP_NULL, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }
        //判断坐席登录方式
        if (agentInfo.getLoginType() == null) {
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.PARAMETER_ERROR, event.getCmd(), event.getAgentKey(), "loginType"));
            event.getChannel().close();
            return;
        }
        switch (agentInfo.getLoginType()) {
            case 1:
            case 2:
                //sip号码不能为空
                if (CollectionUtils.isEmpty(agentInfo.getSips())) {
                    sendMessgae(event, new WsResponseEntity<>(ErrorCode.ACCOUNT_SIP_NOTNUL, event.getCmd(), event.getAgentKey()));
                    event.getChannel().close();
                    return;
                }
                break;
            case 3:
                //手机号不能为空
                if (StringUtils.isBlank(agentInfo.getSipPhone())) {
                    sendMessgae(event, new WsResponseEntity<>(ErrorCode.ACCOUNT_PHONE_NOTNULL, event.getCmd(), event.getAgentKey()));
                    event.getChannel().close();
                    return;
                }
                break;
            default:
                logger.warn("agent:{} loginType:{} not support", event.getAgentKey(), event.getLoginType());
                return;
        }
        agentLogin(agentInfo, event);
    }

    /**
     * 坐席登录
     *
     * @param agentInfo
     * @param event
     */
    private void agentLogin(AgentInfo agentInfo, WsLoginEvnet event) {
        agentInfo.setBeforeTime(agentInfo.getLogoutTime());
        agentInfo.setBeforeState(AgentState.LOGOUT);
        agentInfo.setLoginTime(Instant.now().toEpochMilli());
        agentInfo.setStateTime(agentInfo.getLoginTime());
        agentInfo.setAgentState(AgentState.LOGIN);
        agentInfo.setHost(host);
        agentInfo.setGroupIds(agentService.getAgentGroups(agentInfo.getId()));
        agentInfo.setLoginType(event.getLoginType());
        agentInfo.setWorkType(event.getWorkType());
        agentInfo.setRemoteAddress(event.getChannel().remoteAddress().toString().substring(1));
        cacheService.addAgentInfo(agentInfo);


        /**
         * 授权通过
         */
        ChannelEntity entity = webSocketHandler.getChanel(event.getChannel().id());
        if (entity == null) {
            logger.warn("agent:{} login authorization error", event.getAgentKey());
            return;
        }
        entity.setAuthorization(true);
        entity.setClient(event.getAgentKey());
        webSocketHandler.addAgentChannel(agentInfo.getAgentKey(), entity.getChannel());


        CompanyInfo companyInfo = cacheService.getCompany(agentInfo.getCompanyId());
        if (companyInfo == null) {
            logger.info("company is not available");
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.COMPANY_NOT_AVALIABLE, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }
        //隐藏号码
        agentInfo.setHiddenCustomer(companyInfo.getHiddenCustomer());
        //话单回调
        agentInfo.setCdrNotifyUrl(companyInfo.getNotifyUrl());
        //http坐席状态通知
        agentInfo.setStateNotifyUrl("");

        AgentStateResppnse response = new AgentStateResppnse();
        response.setId(agentInfo.getId());
        response.setCompanyId(agentInfo.getCompanyId());
        response.setGroupId(agentInfo.getGroupId());
        response.setGroupIds(agentInfo.getGroupIds());
        response.setSips(agentInfo.getSips());
        response.setSipPhone(agentInfo.getSipPhone());
        response.setHost(agentInfo.getHost());
        agentInfo.setLogoutTime(0L);

        /**
         * 发送给前端
         */
        sendMessgae(event, new WsResponseEntity<AgentStateResppnse>(event.getCmd(), event.getAgentKey(), response));

        /**
         * 广播坐席状态
         */
        sendAgentStateMessage(agentInfo);
    }
}
