package org.voice9.cc.fs.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.voice9.core.entity.CallDevice;
import com.voice9.core.entity.CallLog;
import com.voice9.core.po.CallInfo;
import com.voice9.core.po.CallLogPo;
import com.voice9.core.po.CompanyInfo;
import com.voice9.core.po.DeviceInfo;
import com.voice9.core.util.DateTimeUtil;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsHangupCompleteEvent;
import org.voice9.cc.fs.handler.base.BaseEventHandler;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by caoliang on 2020/8/23
 */
@Component
@HandlerType("CHANNEL_HANGUP_COMPLETE")
public class FsHangupCompleteHandler extends BaseEventHandler<FsHangupCompleteEvent> {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MinioClient minioClient;

    @Override
    public void handleEvent(FsHangupCompleteEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        logger.info("callId:{} deviceList:{}", callInfo.getCallId(), callInfo.getDeviceList());
        Integer count = callInfo.getDeviceList().size();
        callInfo.getDeviceList().remove(event.getDeviceId());

        /**
         * 挂机原因
         */
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        if (deviceInfo == null) {
            logger.warn("device:{} is null, callId:{}", event.getDeviceId(), callInfo.getCallId());
            return;
        }
        deviceInfo.setHangupCause(event.getHangupCause());
        deviceInfo.setSipProtocol(event.getSipProtocol());
        deviceInfo.setSipStatus(event.getSipStatus());
        deviceInfo.setChannelName(event.getChannelName());
        deviceInfo.setEndTime(event.getTimestamp() / 1000);
        //计算录音时长
        if (deviceInfo.getRecordStartTime() != null) {
            deviceInfo.setRecordTime(deviceInfo.getEndTime() - deviceInfo.getRecordStartTime());
        }
        logger.info("callId:{} deviceId:{} deviceType:{} display:{} called:{} sipStatus:{} sipProtocol:{} hangupCause:{}", callInfo.getCallId(), deviceInfo.getDeviceId(), deviceInfo.getDeviceType(), deviceInfo.getDisplay(), deviceInfo.getCalled(), event.getSipStatus(), event.getSipProtocol(), event.getHangupCause());
        /**
         * 上传录音
         */
        if (StringUtils.isNotBlank(deviceInfo.getRecord())) {
            String day = DateFormatUtils.format(new Date(), "yyyyMMddHH");
            String[] record = deviceInfo.getRecord().split("/");
            String fileName = "/" + day.substring(0, 6) + "/" + day.substring(0, 8) + "/" + day.substring(8, 10) + "/" + record[record.length - 1];
            String url = "http://" + event.getLocalMediaIp() + ":7430" + deviceInfo.getRecord();
            try {
                ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);
                logger.info("get record file:{}", deviceInfo.getRecord());
                ObjectWriteResponse writeResponse = minioClient.putObject(PutObjectArgs.builder().stream(new ByteArrayInputStream(responseEntity.getBody()), responseEntity.getBody().length, -1).object(fileName).bucket("cc-record").build());
                logger.info("callId:{}, record fileName:{}, minioTag:{}", deviceInfo.getCallId(), fileName, writeResponse.etag());
                deviceInfo.setRecord("/cc-record" + fileName);
            } catch (Exception e) {
                logger.error("url:" + url + e.getMessage(), e);
            }
        }

        CallDevice callDevice = new CallDevice();
        BeanUtils.copyProperties(deviceInfo, callDevice);
        callDevice.setCts(deviceInfo.getEndTime() / 1000);
        callDevice.setUts(callDevice.getEndTime() / 1000);
        callDevice.setCompanyId(callInfo.getCompanyId());
        callCdrService.saveCallDevice(callDevice);
        callInfo.getDeviceInfoMap().put(callDevice.getDeviceId(), deviceInfo);

        /**
         * 一般情况下，挂断其他所有设备
         */
        if (deviceInfo.getCdrType() <= 3 && callInfo.getEndTime() == null) {
            if (!CollectionUtils.isEmpty(callInfo.getDeviceList())) {
                callInfo.getDeviceList().forEach(s -> {
                    super.hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), s);
                });
            }
        }
        cacheService.addCallInfo(callInfo);
        //最后一个设备挂机
        if (count == 1) {
            hangup(callInfo);
        }
    }

    /**
     * 全部挂机
     *
     * @param callInfo
     */
    private void hangup(CallInfo callInfo) {
        List<CallDevice> callDeviceList = new ArrayList<>();
        //call_device
        callInfo.getDeviceInfoMap().forEach((k, v) -> {
            CallDevice callDevice = new CallDevice();
            BeanUtils.copyProperties(v, callDevice);
            callDeviceList.add(callDevice);

        });

        CallLog callLog = new CallLog();
        BeanUtils.copyProperties(callInfo, callLog);
        callLog.setCts(callInfo.getCallTime() / 1000);
        callLog.setUts(callInfo.getEndTime() / 1000);
        callLog.setEndTime(callInfo.getEndTime());
        //防止跨月份落单
        String time = DateTimeUtil.format(callLog.getCallTime(), DateTimeUtil.YYYYMM);
        String month = "_" + time.substring(0, 6);
        callLog.setMonthTime(month);

        callLog.setCallType(callInfo.getCallType().name());
        callLog.setDirection(callInfo.getDirection().name());
        if (!CollectionUtils.isEmpty(callInfo.getFollowData())) {
            callLog.setFollowData(JSON.toJSONString(callInfo.getFollowData()));
        }
        if (callInfo.getAnswerTime() == null || callInfo.getAnswerTime() == 0L) {
            //未接通
            callInfo.setTalkTime(0L);
        } else {
            callLog.setTalkTime(callInfo.getEndTime() - callInfo.getAnswerTime());
            callInfo.setTalkTime(callLog.getTalkTime());
            callInfo.setAnswerFlag(0);
        }
        callCdrService.saveCallDetail(callInfo.getCallDetails());
        callCdrService.saveOrUpdateCallLog(callLog);

        CompanyInfo companyInfo = cacheService.getCompany(callInfo.getCompanyId());

        //清空电话信息
        cacheService.removeCallInfo(callInfo.getCallId());
        String notifyUrl = companyInfo.getNotifyUrl();
        if (StringUtils.isBlank(notifyUrl)) {
            return;
        }
        //话单推送
        CallLogPo callLogPo = new CallLogPo();
        BeanUtils.copyProperties(callLog, callLogPo);

        callLogPo.setCallDeviceList(callDeviceList);
        callLogPo.setCaller(callInfo.getCaller());
        callLogPo.setCalled(callInfo.getCalled());
        String payload = JSON.toJSONString(callLogPo, SerializerFeature.WriteMapNullValue);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(payload, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(notifyUrl, requestEntity, String.class);
            logger.info("push call:{} to {} success, statusCode:{} response:{}", callInfo.getCallId(), notifyUrl, responseEntity.getStatusCode(), responseEntity.getBody());
        } catch (Exception e) {
            logger.error("push call:{} to {} error, payload:{}", callInfo.getCallId(), notifyUrl, payload);
        }
    }

}
