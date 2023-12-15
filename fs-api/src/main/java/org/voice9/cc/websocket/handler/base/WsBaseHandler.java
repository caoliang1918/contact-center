package org.voice9.cc.websocket.handler.base;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.mapper.AgentStateLogMapper;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.AgentState;
import com.voice9.core.po.CallInfo;
import com.voice9.core.po.GroupInfo;
import com.voice9.core.util.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.voice9.cc.cache.CacheService;
import org.voice9.cc.command.GroupHandler;
import org.voice9.cc.configration.Handler;
import org.voice9.cc.fs.FsListen;
import org.voice9.cc.fs.esl.transport.message.EslMessage;
import org.voice9.cc.service.AgentService;
import org.voice9.cc.service.CallCdrService;
import org.voice9.cc.websocket.WebSocketHandler;
import org.voice9.cc.websocket.event.base.WsBaseEvent;
import org.voice9.cc.websocket.response.WsResponseEntity;

/**
 * Created by caoliang on 2020/10/29
 */
public abstract class WsBaseHandler<T extends WsBaseEvent> implements Handler<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected CacheService cacheService;

    @Autowired
    protected FsListen fsListen;

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @Autowired
    protected SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    protected AgentService agentService;

    @Autowired
    protected AgentStateLogMapper agentStateLogMapper;

    @Autowired
    protected GroupHandler groupHandler;

    @Autowired
    protected CallCdrService callCdrService;

    @Autowired
    protected WebSocketHandler webSocketHandler;

    /**
     * @param agentInfo
     */
    protected void syncAgentStateMessage(AgentInfo agentInfo) {
        agentService.syncAgentStateMessage(agentInfo);
    }

    /**
     * 电话不存在
     *
     * @param agentInfo
     * @param event
     */
    protected void callNotFound(AgentInfo agentInfo, T event) {
        logger.warn("agentKey:{} not find call", event.getAgentKey());
        agentInfo.setCallId(null);
        agentInfo.setDeviceId(null);
        agentInfo.setAgentState(AgentState.NOT_READY);
        cacheService.addAgentInfo(agentInfo);
        sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
        return;
    }

    /**
     * 给坐席发送消息(ws/http)
     *
     * @param t
     * @param payload
     */
    public void sendMessage(T t, Object payload) {
        if (t.getChannel() == null) {
            return;
        }
        if (!t.getChannel().isActive()) {
            logger.warn("agent:{} is close", t.getAgentKey());
            return;
        }
        logger.info("send agent:{} message:{}", t.getAgentKey(), payload);
        t.getChannel().writeAndFlush(new TextWebSocketFrame(JSON.toJSON(payload).toString()));
    }

    /**
     * 发送坐席消息
     *
     * @param agentInfo
     * @param payload
     */
    public void sendMessage(AgentInfo agentInfo, String payload) {
        webSocketHandler.sendMessgae(agentInfo, payload);
    }


    /**
     * 获取已经登录的坐席
     *
     * @param t
     * @return
     */
    protected AgentInfo getAgent(T t) {
        return cacheService.getAgentInfo(t.getAgentKey());
    }

    protected GroupInfo getGroup(Long id) {
        return cacheService.getGroupInfo(id);
    }

    /**
     * 坐席应答
     *
     * @param media
     * @param devicdId
     * @return
     */
    protected EslMessage answer(String media, String devicdId) {
        return fsListen.answer(media, devicdId);
    }


    /**
     * 挂机
     *
     * @param media
     * @param deivceId
     * @return
     */
    protected void hangupCall(String media, Long callId, String deivceId) {
        fsListen.hangupCall(media, callId, deivceId);
    }

    /**
     * 挂机
     *
     * @param media
     * @param callId
     * @param deivceId
     */
    protected void killCall(String media, Long callId, String deivceId) {
        fsListen.killCall(media, callId, deivceId);
    }

    protected void bridgeCall(String media, Long callId, String deivceId1, String deviceId2) {
        fsListen.bridgeCall(media, callId, deivceId1, deviceId2);
    }

    /**
     * 拆出设备
     *
     * @param media
     * @param deviceId
     */
    protected void bridgeBreak(String media, String deviceId) {
        fsListen.bridgeBreak(media, deviceId);
    }

    /**
     * 坐席静音
     *
     * @param media
     * @param callId
     * @param deviceId
     */
    protected void audioReadMute(String media, Long callId, String deviceId) {
        fsListen.sendBgapiMessage(media, "uuid_audio", deviceId + " start read mute 1");
    }

    /**
     * 取消静音
     *
     * @param media
     * @param callId
     * @param deviceId
     */
    protected void audioStop(String media, Long callId, String deviceId) {
        fsListen.sendBgapiMessage(media, "uuid_audio", deviceId + " stop");
    }

    /**
     * 拆线
     *
     * @param media
     * @param callId
     * @param deviceId
     */
    protected void bridgeBreak(String media, Long callId, String deviceId) {
        fsListen.bridgeBreak(media, deviceId);
    }

    /**
     * 停止放音
     *
     * @param media
     * @param callId
     * @param deviceId
     */
    protected void playBreak(String media, Long callId, String deviceId) {
        fsListen.playBreak(media, deviceId);
    }

    /**
     * 保持
     *
     * @param media
     * @param callId
     * @param deviceId
     * @param play
     */
    protected void holdPlay(String media, Long callId, String deviceId, String play) {
        fsListen.holdPlay(media, deviceId, play);
    }


    /**
     * 随机生成deviceId
     *
     * @return
     */
    protected String getDeviceId() {
        return RandomStringUtils.randomNumeric(16);
    }

    protected String getDeviceId(CallInfo callInfo, String... deviceIds) {
        for (String deviceId : callInfo.getDeviceList()) {
            for (String str : deviceIds) {
                if (!deviceId.equals(str)) {
                    return deviceId;
                }
            }
        }
        return null;
    }

    protected String hiddenNumber(String number) {
        if (StringUtils.isBlank(number)) {
            return null;
        }
        if (number.length() >= 11) {
            return number.substring(0, 3) + "****" + number.substring(7);
        }
        if (number.length() < 3) {
            return number;
        }
        return number.substring(0, 3) + randomHidden(number.length());
    }

    private String randomHidden(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= num; i++) {
            builder.append("*");
        }
        return builder.toString();
    }

}
