package org.voice9.cc.websocket.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.entity.Agent;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.AgentState;
import com.voice9.core.po.CompanyInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.util.BcryptUtil;
import org.voice9.cc.websocket.handler.base.WsBaseHandler;
import org.voice9.cc.websocket.response.AgentStateResppnse;
import org.voice9.cc.websocket.response.WsResponseEntity;
import org.voice9.cc.websocket.event.WsLoginEvnet;
import org.voice9.cc.websocket.event.base.ChannelEntity;

import java.net.InetSocketAddress;
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

    @Value("${server.port}")
    private Integer port;


    @Override
    public void handleEvent(WsLoginEvnet event) {
        logger.info("{}", event.toString());
        AgentInfo agentInfo = null;
        /**
         * 授权通过
         */
        ChannelEntity entity = webSocketHandler.getChanel(event.getChannel().id());
        if (!entity.isAuthorization()) {
            if (StringUtils.isBlank(event.getPasswd()) || StringUtils.isBlank(event.getAgentKey())) {
                logger.info("agent:{} not exist ", event.getAgentKey());
                sendMessage(event, new WsResponseEntity<String>(ErrorCode.ACCOUNT_ERROR, event.getCmd(), event.getAgentKey()));
                event.getChannel().close();
                return;
            }

            //走密码模式
            agentInfo = cacheService.getAgentInfo(event.getAgentKey());
            if (agentInfo == null) {
                logger.info("agent:{} not exist ", event.getAgentKey());
                sendMessage(event, new WsResponseEntity<String>(ErrorCode.ACCOUNT_ERROR, event.getCmd(), event.getAgentKey()));
                event.getChannel().close();
                return;
            }

            if (!BcryptUtil.checkPwd(event.getPasswd(), agentInfo.getPasswd())) {
                logger.error("agent:{}  password {} is error", event.getAgentKey(), event.getPasswd());
                sendMessage(event, new WsResponseEntity<>(ErrorCode.ACCOUNT_ERROR, event.getCmd(), event.getAgentKey()));
                event.getChannel().close();
                return;
            }
            entity.setClient(agentInfo.getAgentKey());
            entity.setAuthorization(true);
            webSocketHandler.addAgentChannel(agentInfo.getAgentKey(), entity.getChannel());
        }


        Channel channel = webSocketHandler.getChannel(entity.getClient());
        if (channel != null && channel.isOpen() && channel.id().equals(event.getChannel().id())) {
            //重复登录成功
            logger.warn("{}:坐席重复登录 ", event.getAgentKey());
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.AGENT_REPEAT_LOGIN, event.getCmd(), event.getAgentKey()));
            return;
        }
        event.setAgentKey(entity.getClient());
        agentInfo = agentService.getAgentInfo(event.getAgentKey());
        if (agentInfo == null) {
            logger.info("agent:{} not exist ", event.getAgentKey());
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.ACCOUNT_ERROR, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }
        if (agentInfo.getGroupId() == null || agentInfo.getGroupId() == 0L) {
            logger.info("agent:{} 技能组为空 , mainGroup is null", event.getAgentKey());
            sendMessage(event, new WsResponseEntity<>(ErrorCode.AGENT_GROUP_NULL, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }
        if (agentInfo.getStatus() == 0) {
            logger.warn("agent:{} 坐席被禁用", event.getAgentKey());
            sendMessage(event, new WsResponseEntity<>(ErrorCode.ACCOUNT_DISABLED, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }

        //通话中不允许挤掉老的连接
        AgentState state = agentInfo.getAgentState();
        if (state != null && (state.name().contains("CALL") || AgentState.TALKING == state)) {
            logger.warn("agentKey:{}, callId:{} ", agentInfo.getAgentKey(), agentInfo.getCallId());
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.AGENT_CALLING, event.getCmd(), event.getAgentKey()));
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
                    entity = webSocketHandler.getChanel(event.getChannel().id());
                    entity.setAuthorization(true);
                    entity.setClient(event.getAgentKey());

                    String payload = JSON.toJSONString(new WsResponseEntity<>(ErrorCode.AGENT_LOGIN_IN_OTHER, event.getCmd(), event.getAgentKey()));
                    logger.warn("send agent:{} message:{}", exist.getClient(), payload);
                    exist.getChannel().writeAndFlush(new TextWebSocketFrame(payload));
                    exist.getChannel().close();
                    webSocketHandler.removeAgentChannel(event.getAgentKey());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        webSocketHandler.addAgentChannel(agentInfo.getAgentKey(), entity.getChannel());

        //判断坐席登录方式
        if (agentInfo.getLoginType() == null) {
            sendMessage(event, new WsResponseEntity<>(ErrorCode.PARAMETER_ERROR, event.getCmd(), event.getAgentKey(), "loginType"));
            event.getChannel().close();
            return;
        }

        switch (agentInfo.getLoginType()) {
            case 1:
            case 2:
                //sip号码不能为空
                if (CollectionUtils.isEmpty(agentInfo.getSips())) {
                    sendMessage(event, new WsResponseEntity<>(ErrorCode.ACCOUNT_SIP_NOTNUL, event.getCmd(), event.getAgentKey()));
                    event.getChannel().close();
                    return;
                }
                break;
            case 3:
                //手机号不能为空
                if (StringUtils.isBlank(agentInfo.getSipPhone())) {
                    sendMessage(event, new WsResponseEntity<>(ErrorCode.ACCOUNT_PHONE_NOTNULL, event.getCmd(), event.getAgentKey()));
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
        agentInfo.setLoginType(event.getLoginType());
        agentInfo.setWorkType(event.getWorkType());
        if (StringUtils.isNotBlank(event.getAgentName())) {
            agentInfo.setAgentName(event.getAgentName());
        }
        //登录时，签入指定技能组
        if (!CollectionUtils.isEmpty(event.getGroupIds())) {
            agentInfo.setGroupIds(event.getGroupIds());
        }

        //记录坐席所在服务器
        InetSocketAddress address = (InetSocketAddress) event.getChannel().localAddress();
        agentInfo.setHost(address.getHostName() + ":" + port);

        AgentState before = AgentState.LOGOUT;
        AgentState now = AgentState.LOGIN;

        agentInfo.setBeforeTime(agentInfo.getLogoutTime());
        agentInfo.setBeforeState(before);
        agentInfo.setLoginTime(Instant.now().getEpochSecond());
        agentInfo.setStateTime(agentInfo.getLoginTime());
        agentInfo.setAgentState(now);
        agentInfo.setGroupIds(agentService.getAgentGroups(agentInfo.getId()));
        agentInfo.setLoginType(event.getLoginType());
        agentInfo.setWorkType(event.getWorkType());
        agentInfo.setRemoteAddress(event.getChannel().remoteAddress().toString().substring(1));


        CompanyInfo companyInfo = cacheService.getCompany(agentInfo.getCompanyId());
        if (companyInfo == null) {
            logger.info("company is not available");
            sendMessage(event, new WsResponseEntity<>(ErrorCode.COMPANY_NOT_AVALIABLE, event.getCmd(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }
        //隐藏号码
        agentInfo.setHiddenCustomer(companyInfo.getHiddenCustomer());

        AgentStateResppnse response = new AgentStateResppnse();
        response.setId(agentInfo.getId());
        response.setCompanyId(agentInfo.getCompanyId());
        response.setGroupId(agentInfo.getGroupId());
        response.setGroupIds(agentInfo.getGroupIds());
        response.setSips(agentInfo.getSips());
        response.setSipPhone(agentInfo.getSipPhone());
        response.setHost(agentInfo.getHost());
        agentInfo.setLogoutTime(0L);

        cacheService.addAgentInfo(agentInfo);

        /**
         * 发送给前端
         */
        sendMessage(event, new WsResponseEntity<AgentStateResppnse>(event.getCmd(), event.getAgentKey(), response));

        /**
         * 广播坐席状态
         */
        syncAgentStateMessage(agentInfo);

        /**
         * 坐席在线
         */
        Agent agent = new Agent();
        agent.setId(agentInfo.getId());
        agent.setCompanyId(agentInfo.getCompanyId());
        agent.setState(1);
        agent.setHost(agentInfo.getHost());
        agentService.editById(agent);
    }
}
