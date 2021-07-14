package org.zhongweixian.cc.command;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.cti.cc.po.NextCommand;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.command.base.BaseHandler;

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

        logger.info("callId:{} find agent:{}", callInfo.getCallId(), agentInfo.getAgentKey());
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setCaller(agentInfo.getAgentId());
        deviceInfo.setDisplay(callInfo.getCaller());
        deviceInfo.setCalled(caller);
        deviceInfo.setCallTime(Instant.now().toEpochMilli());
        deviceInfo.setCallId(callInfo.getCallId());
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setDeviceType(1);
        deviceInfo.setNextCommand(new NextCommand(NextType.NEXT_CALL_BRIDGE, thisDeviceId));
        deviceInfo.setAgentKey(agentInfo.getAgentKey());
        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        callInfo.setAgentKey(agentInfo.getAgentKey());
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), caller);
        if (routeGetway == null) {
            fsListen.hangupCall(callInfo.getMedia(), callInfo.getCallId(), thisDeviceId);
            return;
        }
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceId);
        fsListen.makeCall(routeGetway, callInfo.getCaller(), caller, deviceId);
    }
}
