package org.zhongweixian.cc.listen;

import com.alibaba.fastjson.JSONObject;
import org.cti.cc.constant.Constants;
import org.cti.cc.entity.AgentStateLog;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.entity.CallLog;
import org.cti.cc.mapper.AgentStateLogMapper;
import org.cti.cc.mapper.CallDetailMapper;
import org.cti.cc.mapper.CallDeviceMapper;
import org.cti.cc.mapper.CallLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by caoliang on 2021/8/3
 */
@Component
public class CallCdrListen {
    private Logger logger = LoggerFactory.getLogger(CallCdrListen.class);

    @Value("${cc.pressure:0}")
    private Integer pressure;

    @Autowired
    private CallLogMapper callLogMapper;

    @Autowired
    private CallDetailMapper callDetailMapper;

    @Autowired
    private CallDeviceMapper callDeviceMapper;

    @Autowired
    private AgentStateLogMapper agentStateLogMapper;

    @RabbitListener(queues = Constants.AGENT_STATE_LOG_QUEUE)
    public void listenAgentStateLog(@Payload String payload) {
        AgentStateLog agentStateLog = JSONObject.parseObject(payload, AgentStateLog.class);
        if (agentStateLog != null && pressure == 0) {
            agentStateLogMapper.insertSelective(agentStateLog);
        }
    }

    /**
     * 话单
     *
     * @param payload
     */
    @RabbitListener(queues = Constants.CALL_DEVICE_QUEUE)
    public void listenCallDevice(@Payload String payload) {
        CallDevice callDevice = JSONObject.parseObject(payload, CallDevice.class);
        if (callDevice != null) {
            callDeviceMapper.insertSelective(callDevice);
        }
    }

    @RabbitListener(queues = Constants.CALL_DETAIL_QUEUE)
    public void listenCallDetailQueue(@Payload String payload) {
        CallDetail callDetail = JSONObject.parseObject(payload, CallDetail.class);
        logger.info("{}", payload);
        if (callDetail != null) {
            callDetailMapper.insertSelective(callDetail);
        }
    }

    /**
     * 话单
     *
     * @param payload
     */
    @RabbitListener(queues = Constants.CALL_LOG_QUEUE)
    public void listenCallLog(@Payload String payload) {
        logger.info("callLog:{}", payload);
        CallLog callLog = JSONObject.parseObject(payload, CallLog.class);
        logger.info("callId:{} , answerTime:{}", callLog.getCallId(), callLog.getAnswerTime());
        if (callLog.getAnswerTime() != null && callLog.getEndTime() == null) {
            //呼通
            callLogMapper.insertSelective(callLog);
            return;
        }
        if (callLog.getAnswerTime() == null && callLog.getEndTime() != null) {
            //没有呼通
            callLogMapper.insertSelective(callLog);
            return;
        }
        int result = callLogMapper.updateByCallId(callLog);
        if (result == 0) {
            callLogMapper.insertSelective(callLog);
        }
    }
}
