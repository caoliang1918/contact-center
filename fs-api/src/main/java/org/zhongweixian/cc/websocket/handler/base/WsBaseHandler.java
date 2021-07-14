package org.zhongweixian.cc.websocket.handler.base;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.RandomStringUtils;
import org.cti.cc.mapper.AgentStateLogMapper;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.GroupInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.GroupHandler;
import org.zhongweixian.cc.configration.Handler;
import org.zhongweixian.cc.fs.FsListen;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.service.CallCdrService;
import org.zhongweixian.cc.util.SnowflakeIdWorker;
import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;
import org.zhongweixian.esl.transport.message.EslMessage;

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
    private AgentService agentService;

    @Autowired
    protected AgentStateLogMapper agentStateLogMapper;

    @Autowired
    protected GroupHandler groupHandler;

    @Autowired
    protected CallCdrService callCdrService;


    /**
     * @param agentInfo
     */
    protected void sendAgentStateMessage(AgentInfo agentInfo) {
        agentService.sendAgentStateMessage(agentInfo);
    }


    /**
     * 给坐席发送消息(ws/http)
     *
     * @param t
     * @param payload
     */
    public void sendMessgae(T t, Object payload) {
        if (!t.getChannel().isActive()) {
            logger.warn("agent:{} is close", t.getAgentKey());
            return;
        }
        logger.info("send agent:{} message:{}", t.getAgentKey(), payload);
        t.getChannel().writeAndFlush(new TextWebSocketFrame(JSON.toJSON(payload).toString()));
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
}
