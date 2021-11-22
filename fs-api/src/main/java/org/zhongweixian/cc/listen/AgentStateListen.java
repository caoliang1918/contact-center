package org.zhongweixian.cc.listen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.cti.cc.constant.Constants;
import org.cti.cc.entity.AgentStateLog;
import org.cti.cc.mapper.AgentStateLogMapper;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.GroupHandler;
import org.zhongweixian.cc.service.CallCdrService;
import org.zhongweixian.cc.websocket.WebSocketHandler;
import org.zhongweixian.cc.websocket.response.AgentStateResppnse;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

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
     *
     * @param record
     * @param ack
     */
    @KafkaListener(topics = {Constants.AGENT_STATE}, groupId = "${spring.application.name}")
    public void listenAgentState(ConsumerRecord<String, String> record, Acknowledgment ack) {
        logger.debug("receive agent state payload  {} ", record.value());
        if (StringUtils.isBlank(record.value())) {
            return;
        }
        ack.acknowledge();
        String payload = record.value();
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


    @KafkaListener(topics = Constants.AGENT_STATE_LOG, groupId = "${spring.application.name}")
    public void listenAgentStateLog(ConsumerRecord<String, String> record, Acknowledgment ack) {
        AgentStateLog agentStateLog = JSONObject.parseObject(record.value(), AgentStateLog.class);
        ack.acknowledge();
        if (agentStateLog == null || pressure == 1) {
            return;
        }
        agentStateLog.setCts(Instant.now().getEpochSecond());
        agentStateLog.setUts(agentStateLog.getCts());
        agentStateLogMapper.insertSelective(agentStateLog);


        String time = DateTimeUtil.getNowMonth();
        agentStateLog.setMonth(time);
        try {
            //当前月份表
            agentStateLogMapper.insertMonthSelective(agentStateLog);
        } catch (Exception e) {
            if (e.getMessage().contains("doesn't exist")) {
                /**
                 * 初始化月表
                 */
                callCdrService.subTable(DateTimeUtil.getNowMonth());
                return;
            }
            logger.error(e.getMessage(), e);
        }
    }
}
