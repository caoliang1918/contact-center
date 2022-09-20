package org.zhongweixian.cc.fs.handler;

import java.time.Instant;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.CallSpeechText;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.DetectedSpeechEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

/**
 * Created by caoliang on 2022/6/15
 */
@Component
@HandlerType("DETECTED_SPEECH")
public class DetectedSpeechHandler extends BaseEventHandler<DetectedSpeechEvent> {

    @Override
    public void handleEvent(DetectedSpeechEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        logger.info("callId:{}, deviceId:{}, detectedSpeech type:{}", callInfo.getCallId(), event.getDeviceId(), event.getType());
        if (CollectionUtils.isEmpty(event.getSpeech())) {
            return;
        }
        CallSpeechText callSpeechText = new CallSpeechText();
        for (String s : event.getSpeech()) {
            logger.info("{}", s);
            if (s.contains("Completion-Cause: 002")) {
              
                continue;
            }
            if (s.contains("input")) {
                s = s.trim();
                callSpeechText.setSpeechText(s.substring(21, s.indexOf("</input>")));
            }
        }
        //fsListen.asrResume(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId());
        try {
            if (StringUtils.isBlank(callSpeechText.getSpeechText())) {
                return;
            }
            logger.info("callId:{} deviceId:{} deviceType:{}  speechText:{}", deviceInfo.getCallId(), deviceInfo.getDeviceId(), deviceInfo.getDeviceType(), callSpeechText.getSpeechText());

            callSpeechText.setCallId(deviceInfo.getCallId());
            callSpeechText.setDeviceId(deviceInfo.getDeviceId());
            callSpeechText.setDeviceType(deviceInfo.getDeviceType());
            callSpeechText.setCts(Instant.now().getEpochSecond());
            callSpeechText.setSpeechId(RandomStringUtils.randomNumeric(16));
            //callCdrService.saveCallSpeech(callSpeechText);

            //callInfo.getSpeechTexts().add(callSpeechText);
            cacheService.addCallInfo(callInfo);

            AgentInfo agentInfo = cacheService.getAgentInfo(callInfo.getAgentKey());
            if (agentInfo == null) {
                return;
            }
            //AsrSpeechEntity entity = new AsrSpeechEntity();
            //BeanUtils.copyProperties(callSpeechText, entity);
            //super.sendWsMessage(agentInfo, new WsResponseEntity<AsrSpeechEntity>(AgentState.ASR_SPEECH.name(), agentInfo.getAgentKey(), entity));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
