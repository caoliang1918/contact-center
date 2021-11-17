package org.zhongweixian.cc.listen;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.cti.cc.constant.Constants;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.entity.CallLog;
import org.cti.cc.mapper.CallDetailMapper;
import org.cti.cc.mapper.CallDeviceMapper;
import org.cti.cc.mapper.CallLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by caoliang on 2021/8/3
 */
@Component
public class CallCdrListen {
    private Logger logger = LoggerFactory.getLogger(CallCdrListen.class);

    @Autowired
    private CallLogMapper callLogMapper;

    @Autowired
    private CallDetailMapper callDetailMapper;

    @Autowired
    private CallDeviceMapper callDeviceMapper;


    /**
     * @param record
     */
    @KafkaListener(topics = Constants.CALL_DEVICE, groupId = "${spring.application.name}")
    public void listenCallDevice(ConsumerRecord<String, String> record) {
        CallDevice callDevice = JSONObject.parseObject(record.value(), CallDevice.class);
        if (callDevice != null) {
            callDeviceMapper.insertSelective(callDevice);
        }
    }

    /**
     * @param record
     */
    @KafkaListener(topics = Constants.CALL_DETAIL, groupId = "${spring.application.name}")
    public void listenCallDetailQueue(ConsumerRecord<String, String> record) {
        CallDetail callDetail = JSONObject.parseObject(record.value(), CallDetail.class);
        logger.info("{}", record.value());
        if (callDetail != null) {
            callDetailMapper.insertSelective(callDetail);
        }
    }

    /**
     * 话单
     *
     * @param record
     */
    @KafkaListener(topics = Constants.CALL_LOG, groupId = "${spring.application.name}")
    public void listenCallLog(ConsumerRecord<String, String> record) {
        logger.info("callLog:{}", record.value());
        CallLog callLog = JSONObject.parseObject(record.value(), CallLog.class);
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
