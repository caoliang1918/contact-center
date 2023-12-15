package org.voice9.cc.websocket.handler;

import com.voice9.core.po.*;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.enums.NextType;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.websocket.handler.base.WsBaseHandler;
import org.voice9.cc.websocket.event.WsHangupCallEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2020/11/6
 * <p>
 * 坐席发起挂机
 */
@Component
@HandlerType("WS_HANGUP_CALL")
public class WsHangupCallHandler extends WsBaseHandler<WsHangupCallEvent> {

    @Override
    public void handleEvent(WsHangupCallEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo.getCallId() == null || StringUtils.isBlank(agentInfo.getDeviceId())) {
            callNotFound(agentInfo, event);
            return;
        }

        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(agentInfo.getDeviceId());

        if (callInfo == null || deviceInfo == null || StringUtils.isBlank(callInfo.getMediaHost())) {
            callNotFound(agentInfo, event);
            return;
        }

        List<String> strings = new ArrayList<>(callInfo.getDeviceList());

        switch (deviceInfo.getCdrType()) {
            case 1:
            case 2:
            case 3:
            case 4:
                logger.info("坐席发起挂机 agent:{} callId:{}", event.getAgentKey(), callInfo.getCallId());
                hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), agentInfo.getDeviceId());
                callInfo.setSdkHangup(1);
                break;

            case 5:
                strings.remove(deviceInfo.getDeviceId());
                DeviceInfo deviceInfo1 = null;
                for (String s : strings) {
                    deviceInfo1 = callInfo.getDeviceInfoMap().get(s);
                    if (AgentState.HOLD.name().equals(deviceInfo1.getState())) {
                        break;
                    }
                }
                if (deviceInfo1 != null) {
                    strings.remove(deviceInfo1.getDeviceId());
                    NextCommand nextCommand = null;
                    if (StringUtils.isBlank(deviceInfo1.getConference())) {
                        nextCommand = new NextCommand(strings.get(0), NextType.NEXT_CALL_BRIDGE, deviceInfo1.getDeviceId());
                    } else {
                        nextCommand = new NextCommand(NextType.NORNAL);
                    }
                    callInfo.getNextCommands().add(nextCommand);
                    hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), agentInfo.getDeviceId());
                }
                break;
            case 6:
            case 7:
            case 8:
                NextCommand nextCommand = new NextCommand(NextType.NORNAL);
                callInfo.getNextCommands().add(nextCommand);
                killCall(callInfo.getMediaHost(), callInfo.getCallId(), agentInfo.getDeviceId());
        }
        cacheService.addCallInfo(callInfo);
    }
}
