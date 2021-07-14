package org.zhongweixian.cc.service.impl;

import com.alibaba.fastjson.JSON;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.entity.CallLog;
import org.cti.cc.entity.PushFailLog;
import org.cti.cc.mapper.CallDetailMapper;
import org.cti.cc.mapper.CallDeviceMapper;
import org.cti.cc.mapper.CallLogMapper;
import org.cti.cc.mapper.PushFailLogMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.CallLogPo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.mq.RabbitConfig;
import org.zhongweixian.cc.service.CallCdrService;

/**
 * Create by caoliang on 2020/10/28
 */
@Component
public class CallCdrServiceImpl extends BaseServiceImpl<CallLog> implements CallCdrService {

    @Autowired
    private CallLogMapper callLogMapper;

    @Autowired
    private CallDetailMapper callDetailMapper;

    @Autowired
    private CallDeviceMapper callDeviceMapper;

    @Autowired
    private PushFailLogMapper pushFailLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${call.cdr.mq:0}")
    private Integer callCdrMq;


    @Override
    BaseMapper<CallLog> baseMapper() {
        return callLogMapper;
    }

    @Override
    public int saveCallDevice(CallDevice callDevice) {
        if (callCdrMq == 1) {
            rabbitTemplate.convertAndSend(RabbitConfig.CALL_DEVICE_EXCHANGE, RabbitConfig.CALL_DEVICE_ROUTING, JSON.toJSONString(callDevice));
            return 0;
        }
        return callDeviceMapper.insertSelective(callDevice);
    }

    @Override
    public int saveCallDetail(CallDetail callDetail) {
        return callDetailMapper.insertSelective(callDetail);
    }

    @Override
    public int saveOrUpdateCallLog(CallLog callLog) {
        if (callLog == null) {
            return 0;
        }
        logger.info("callId:{} , answerTime:{}", callLog.getCallId(), callLog.getAnswerTime());
        if (callCdrMq == 1) {
            rabbitTemplate.convertAndSend(RabbitConfig.CALL_LOG_EXCHANGE, RabbitConfig.CALL_LOG_ROUTING, JSON.toJSONString(callLog));
            return 0;
        }

        if (callLog.getAnswerTime() != null && callLog.getEndTime() == null) {
            //呼通
            return callLogMapper.insertSelective(callLog);
        }
        if (callLog.getAnswerTime() == null && callLog.getEndTime() != null) {
            //没有呼通
            return callLogMapper.insertSelective(callLog);
        }
        int result = callLogMapper.updateByCallId(callLog);
        if (result == 0) {
            result = callLogMapper.insertSelective(callLog);
        }
        return result;
    }

    @Override
    public CallLogPo getCall(Long companyId, Long callId) {
        return callLogMapper.getCall(companyId, callId);
    }

    @Override
    public int savePushFailLog(PushFailLog pushFailLog) {
        return pushFailLogMapper.insertSelective(pushFailLog);
    }
}
