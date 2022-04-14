package org.zhongweixian.cc.websocket.handler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsConsultPartyEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsConferenceResponse;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.util.Map;

/**
 * Created by caoliang on 2022/2/8.
 * <p>
 * 咨询多方会议
 * 1、先把发起方从bridge中拆出
 * 2、用户break
 * 3、发起坐席、用户，被咨询坐席同时执行 conference
 */
@Component
@HandlerType("WS_CONSULT_PARTY")
public class WsConsultPartyHandler extends WsBaseHandler<WsConsultPartyEvent> {
    @Override
    public void handleEvent(WsConsultPartyEvent event) {
        AgentInfo agentInfo = getAgent(event);

        //当前通话中的电话
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (agentInfo.getCallId() == null || callInfo == null || StringUtils.isBlank(agentInfo.getConsultDeviceId())) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }

        //被咨询设备
        DeviceInfo consultDevice = callInfo.getDeviceInfoMap().get(agentInfo.getConsultDeviceId());
        if (consultDevice == null) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }

        if (!callInfo.getCallId().equals(consultDevice.getCallId())) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }


        WsConferenceResponse response = new WsConferenceResponse();
        response.setCallId(callInfo.getCallId());
        response.setCallType(callInfo.getCallType());
        response.setAgentState(AgentState.CONFERENCE_TALKING);

        /**
         * 已经发起过会议,再次加入会议
         */
        if (!StringUtils.isBlank(callInfo.getConference())) {
            consultDevice.setConference(callInfo.getConference());
            joinConference(callInfo.getMediaHost(), callInfo.getCallId(), consultDevice.getDeviceId(), callInfo.getConference());
            agentInfo.setConsultDeviceId(null);
            if (consultDevice.getDeviceType() == 1) {
                AgentInfo consultAgent = cacheService.getAgentInfo(consultDevice.getAgentKey());
                response.getConferenceList().add(consultAgent.getAgentKey().split("@")[0]);
                sendMessage(consultAgent, JSON.toJSONString(new WsResponseEntity<WsConferenceResponse>(AgentState.TALKING.name(), consultDevice.getAgentKey(), response)));
            }

            response.getConferenceList().clear();
            for (String device : callInfo.getDeviceList()) {
                DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(device);
                if (StringUtils.isBlank(deviceInfo.getConference()) || deviceInfo.getDeviceId().equals(agentInfo.getDeviceId())) {
                    continue;
                }
                if (deviceInfo.getDeviceType() == 1) {
                    //咨询坐席
                    response.getConferenceList().add(deviceInfo.getAgentKey().split("@")[0]);
                } else if (deviceInfo.getDeviceType() == 3) {
                    //咨询外线
                    response.getConferenceList().add(deviceInfo.getCalled());
                }
            }
            logger.info("callId:{} , agent:{} on conference for:{}", callInfo.getCallId(), agentInfo.getAgentKey(), response.getConferenceList());
            sendMessage(agentInfo, JSON.toJSONString(new WsResponseEntity<WsConferenceResponse>(AgentState.TALKING.name(), consultDevice.getAgentKey(), response)));
            cacheService.addAgentInfo(agentInfo);
            return;
        }


        /**
         * 首次加入会议
         */
        //先把发起方从bridge中拆出
        bridgeBreak(callInfo.getMediaHost(), agentInfo.getDeviceId());
        for (Map.Entry<String, DeviceInfo> entry : callInfo.getDeviceInfoMap().entrySet()) {
            if (AgentState.HOLD.name().equals(entry.getValue().getState()) && entry.getValue().getEndTime() == null) {
                //break
                playBreak(callInfo.getMediaHost(), callInfo.getCallId(), entry.getKey());
            }
        }

        String conference = getDeviceId();
        logger.info("create conference, callId:{} , conferenceId:{}", callInfo.getCallId(), conference);
        callInfo.getDeviceList().forEach(deviceId -> {
            DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(deviceId);
            deviceInfo.setConference(conference);
            joinConference(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId(), callInfo.getConference());
            //被咨询坐席通知
            if (deviceInfo.getDeviceType() == 1 && !deviceInfo.getDeviceId().equals(agentInfo.getDeviceId())) {
                AgentInfo consultAgent = cacheService.getAgentInfo(deviceInfo.getAgentKey());
                response.getConferenceList().add(consultAgent.getAgentKey().split("@")[0]);
                sendMessage(consultAgent, JSON.toJSONString(new WsResponseEntity<WsConferenceResponse>(AgentState.TALKING.name(), consultDevice.getAgentKey(), response)));
            }
        });
        response.getConferenceList().clear();
        for (String device : callInfo.getDeviceList()) {
            DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(device);
            if (StringUtils.isBlank(deviceInfo.getConference()) || deviceInfo.getDeviceId().equals(agentInfo.getDeviceId())) {
                continue;
            }
            if (deviceInfo.getDeviceType() == 1) {
                //咨询坐席
                response.getConferenceList().add(deviceInfo.getAgentKey().split("@")[0]);
            } else if (deviceInfo.getDeviceType() == 3) {
                //咨询外线
                response.getConferenceList().add(deviceInfo.getCalled());
            }
        }
        sendMessage(agentInfo, JSON.toJSONString(new WsResponseEntity<WsConferenceResponse>(AgentState.TALKING.name(), consultDevice.getAgentKey(), response)));

        callInfo.setConference(conference);
        cacheService.addCallInfo(callInfo);

        agentInfo.setConsultDeviceId(null);
        cacheService.addAgentInfo(agentInfo);
    }


}
