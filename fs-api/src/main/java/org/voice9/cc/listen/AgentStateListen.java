package org.voice9.cc.listen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.voice9.core.constant.Constant;
import com.voice9.core.entity.AgentStateLog;
import com.voice9.core.mapper.AgentStateLogMapper;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.AgentState;
import com.voice9.core.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.voice9.cc.cache.CacheService;
import org.voice9.cc.command.GroupHandler;
import org.voice9.cc.service.CallCdrService;
import org.voice9.cc.websocket.WebSocketHandler;
import org.voice9.cc.websocket.response.AgentStateResppnse;
import org.voice9.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Create by caoliang on 2020/11/1
 */
@Component
public class AgentStateListen {
    private Logger logger = LoggerFactory.getLogger(AgentStateListen.class);


    @Autowired
    private CacheService cacheService;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Autowired
    private GroupHandler groupHandler;

    @Autowired
    private AgentStateLogMapper agentStateLogMapper;

    @Autowired
    private CallCdrService callCdrService;

    @Value("${spring.application.group}" + "${spring.instance.id}")
    private String appId;

    @Value("${agent.state.log.pressure:0}")
    private Integer pressure;

    /**
     * 同步坐席状态
     */
    /*@KafkaListener(topics = {Constants.AGENT_STATE}, groupId = "${spring.application.name}")*/
    @RabbitListener(bindings = {@QueueBinding(exchange = @Exchange(value = Constant.AGENT_STATE_EXCHANGE, type = ExchangeTypes.TOPIC), key = Constant.AGENT_STATE_KEY, value = @Queue(value = Constant.AGENT_STATE_QUEUE + Constant.LINE+ "${spring.instance.id}", autoDelete = "true"))})
    public void listenAgentState(@Payload String payload) {
        logger.debug("receive agent state payload  {} ", payload);
        JSONObject json = JSON.parseObject(payload);
        if (appId.equals(json.getString("appId"))) {
            return;
        }

        AgentStateResppnse resppnse = JSON.parseObject(payload, AgentStateResppnse.class);
        AgentInfo agentInfo = cacheService.getAgentInfo(json.getString("agentKey"));
        if (agentInfo == null) {
            logger.info("receive agent {} change state {}", json.getString("agentKey"), json.getString("agentState"));
            agentInfo = new AgentInfo();
            BeanUtils.copyProperties(resppnse, agentInfo);
            cacheService.addAgentInfo(agentInfo);
            return;
        }
        String state = json.getString("agentState");

        if (agentInfo.getHost().equals(json.getString("host"))) {
            agentInfo.setAgentKey(resppnse.getAgentKey());
            agentInfo.setCompanyId(resppnse.getCompanyId());
            agentInfo.setHost(resppnse.getHost());
            agentInfo.setCallId(resppnse.getCallId());
            agentInfo.setLoginTime(resppnse.getLoginTime());
            agentInfo.setAgentState(resppnse.getAgentState());
            agentInfo.setLoginType(resppnse.getLoginType());
            agentInfo.setWorkType(resppnse.getWorkType());
            agentInfo.setStateTime(resppnse.getStateTime());
            agentInfo.setBeforeState(resppnse.getBeforeState());
            agentInfo.setBeforeTime(resppnse.getBeforeTime());
            agentInfo.setMaxReadyTime(resppnse.getMaxReadyTime());
            agentInfo.setTotalReadyTime(resppnse.getTotalReadyTime());
            agentInfo.setMaxTalkTime(resppnse.getMaxTalkTime());
            agentInfo.setTotalTalkTime(resppnse.getTotalTalkTime());
            agentInfo.setTotalRingTimes(resppnse.getTotalRingTimes());
            agentInfo.setTotalAnswerTimes(resppnse.getTotalAnswerTimes());
            agentInfo.setReadyTimes(resppnse.getReadyTimes());
            agentInfo.setNotReadyTimes(resppnse.getNotReadyTimes());
            agentInfo.setTotalAfterTime(resppnse.getTotalAfterTime());
            agentInfo.setServiceTime(resppnse.getServiceTime());
            logger.info("receive agent {} change state {}", agentInfo.getAgentKey(), agentInfo.getAgentState());
            if (agentInfo.getAgentState() == AgentState.READY) {
                groupHandler.agentFree(agentInfo);
            }
            if (agentInfo.getBeforeState() == AgentState.READY) {
                groupHandler.agentNotReady(agentInfo);
            }
            cacheService.addAgentInfo(agentInfo);
            return;
        }

        /**
         * 呼入转坐席，坐席和电话不在一个服务上
         */
        /*if (station.getHost().equals(json.getString("host")) && !appId.equals(json.getString("appId"))) {
            webSocketHandler.sentWsMessage(agentInfo, record.value());
            return;
        }*/


        if (state.equals(AgentState.LOGIN.name())) {
            //d当前坐席退出
            logger.info("agent:{} login on server:{}", agentInfo.getAgentKey(), agentInfo.getHost());
            String logoutResponse = JSON.toJSONString(new WsResponseEntity<>(1005, "坐席在别处登录", AgentState.LOGOUT.name(), agentInfo.getAgentKey()));
            webSocketHandler.sentWsMessage(agentInfo, logoutResponse);
            webSocketHandler.close(agentInfo.getAgentKey());
            agentInfo.setHost(json.getString("host"));
            agentInfo.setAgentState(AgentState.LOGIN);
            return;
        }
    }


    /**
     * 坐席状态数据
     *
     * @param payload
     */
    @RabbitListener(bindings = {@QueueBinding(exchange = @Exchange(value = Constant.AGENT_STATE_EXCHANGE, type = ExchangeTypes.TOPIC), key = Constant.AGENT_LOG_KEY, value = @Queue(Constant.AGENT_LOG_QUEUE))})
    public void listenAgentStateLog(@Payload String payload) {
        AgentStateLog agentStateLog = JSONObject.parseObject(payload, AgentStateLog.class);
        if (agentStateLog == null || pressure == 1) {
            return;
        }
        agentStateLog.setCts(Instant.now().getEpochSecond());
        agentStateLog.setUts(agentStateLog.getCts());

        String time = DateTimeUtil.getNowMonth();
        agentStateLog.setMonth(time);
        try {
            agentStateLogMapper.insertSelective(agentStateLog);
            //当前月份表
            agentStateLogMapper.insertMonthSelective(agentStateLog);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
