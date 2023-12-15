package org.voice9.cc.command;

import org.apache.commons.lang3.StringUtils;
import com.voice9.core.entity.RouteGetway;
import com.voice9.core.enums.NextType;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.CallInfo;
import com.voice9.core.po.DeviceInfo;
import com.voice9.core.po.NextCommand;
import org.springframework.stereotype.Component;
import org.voice9.cc.command.base.BaseHandler;

import java.time.Instant;

/**
 * Created by caoliang on 2020/11/6
 */

@Component
public class TransferAgentHandler extends BaseHandler {

    /**
     * 转接座席
     *
     * @param callInfo
     * @param agentInfo
     * @param thisDeviceId
     */
    public void hanlder(CallInfo callInfo, AgentInfo agentInfo, String thisDeviceId) {
        String deviceId = getDeviceId();
        String caller = agentInfo.getCalled();
        //坐席没有sip或者分机不存在
        if (StringUtils.isBlank(caller)) {
            logger.error("agent:{} sip is error, callId:{}", agentInfo.getAgentKey(), callInfo.getCallId());
            return;
        }
        if (callInfo.getHiddenCustomer() == 1) {

        }

        logger.info("callId:{} find agent:{}", callInfo.getCallId(), agentInfo.getAgentKey());
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setCaller(agentInfo.getAgentId());
        //deviceInfo.setDisplay(callInfo.getHiddenCustomer() == 1 ? hiddenNumber(callInfo.getCaller()) : callInfo.getCaller());
        deviceInfo.setDisplay(callInfo.getCaller());
        deviceInfo.setCalled(caller);
        deviceInfo.setCallTime(Instant.now().toEpochMilli());
        deviceInfo.setCallId(callInfo.getCallId());
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setDeviceType(1);
        deviceInfo.setAgentKey(agentInfo.getAgentKey());
        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        callInfo.setAgentKey(agentInfo.getAgentKey());
        callInfo.setCalled(agentInfo.getCalled());
        callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_CALL_BRIDGE, thisDeviceId));
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), caller);
        if (routeGetway == null) {
            fsListen.hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), thisDeviceId);
            return;
        }
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceId);
        fsListen.makeCall(routeGetway, callInfo.getCaller(), caller, callInfo.getCallId(), deviceId, null, null);
    }
}
