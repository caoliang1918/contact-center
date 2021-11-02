package org.zhongweixian.cc.listen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.cache.CacheService;
import org.zhongweixian.cc.command.GroupHandler;
import org.zhongweixian.cc.websocket.WebSocketHandler;
import org.zhongweixian.cc.websocket.response.AgentStateResppnse;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

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

    @Value("${spring.application.group}" + "${spring.instance.id}")
    private String appId;

    /**
     * 同步坐席状态
     *
     * @param record
     */
    @KafkaListener(topics = "sync.agent" + "${spring.application.group}", groupId = "${spring.instance.id}")
    public void listenAgentState(ConsumerRecord<String, String> record) {
        JSONObject json = JSON.parseObject(record.value());
        if (appId.equals(json.getString("appId"))) {
            return;
        }

        AgentStateResppnse resppnse = JSON.parseObject(record.value(), AgentStateResppnse.class);
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
}
