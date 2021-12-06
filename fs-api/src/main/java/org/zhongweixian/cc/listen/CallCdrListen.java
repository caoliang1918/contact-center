package org.zhongweixian.cc.listen;

import com.alibaba.fastjson.JSONObject;
import org.cti.cc.constant.Constants;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallDevice;
import org.cti.cc.entity.CallLog;
import org.cti.cc.mapper.CallDetailMapper;
import org.cti.cc.mapper.CallDeviceMapper;
import org.cti.cc.mapper.CallLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
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
     * call_device落单
     *
     * @param payload
     */
    @RabbitListener(bindings = {@QueueBinding(exchange=@Exchange(value = Constants.CALL_LOG_EXCHANGE, type = ExchangeTypes.TOPIC),key = Constants.DEVOCE_KEY , value =  @Queue( Constants.CALL_DEVICE_QUEUE))})
    public void listenCallDevice(@Payload String payload) {
        try {
            CallDevice callDevice = JSONObject.parseObject(payload, CallDevice.class);
            if (callDevice != null) {
                callDeviceMapper.insertSelective(callDevice);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * @param payload
     */
    @RabbitListener(bindings = {@QueueBinding(exchange=@Exchange(value = Constants.CALL_LOG_EXCHANGE, type = ExchangeTypes.TOPIC),key = Constants.DETAIL_KEY , value =  @Queue( Constants.CALL_DETAIL_QUEUE))})
    public void listenCallDetailQueue(@Payload String payload) {
        try {
            CallDetail callDetail = JSONObject.parseObject(payload, CallDetail.class);
            if (callDetail != null) {
                callDetailMapper.insertSelective(callDetail);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 话单
     *
     * @param payload
     */
    @RabbitListener(bindings = {@QueueBinding(exchange=@Exchange(value = Constants.CALL_LOG_EXCHANGE, type = ExchangeTypes.TOPIC),key = Constants.CALLLOG_KEY , value =  @Queue( Constants.CALL_LOG_QUEUE))})
    public void listenCallLog(@Payload String payload) {
        logger.info("callLog:{}", payload);
        try {
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

            if (callLog.getEndTime() != null) {
                callLogMapper.insertMonthSelective(callLog);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
