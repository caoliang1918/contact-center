package org.zhongweixian.cc.websocket.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.cti.cc.po.NextCommand;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsConsultTransferEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2022/2/8.
 * <p>
 * 咨询转
 */
@Component
@HandlerType("WS_CONSULT_TRANSFER")
public class WsConsultTransferHandler extends WsBaseHandler<WsConsultTransferEvent> {
    @Override
    public void handleEvent(WsConsultTransferEvent event) {
        AgentInfo agentInfo = getAgent(event);
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (callInfo == null || StringUtils.isBlank(agentInfo.getConsultDeviceId())) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }
        //拿到被咨询设备
        DeviceInfo consultDevice = callInfo.getDeviceInfoMap().get(agentInfo.getConsultDeviceId());
        if (consultDevice == null || consultDevice.getCallId() == null) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }

        //先把被咨询坐席拆出
        bridgeBreak(callInfo.getMediaHost(), consultDevice.getDeviceId());

        //咨询发起方挂机
        hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), agentInfo.getDeviceId());

        List<String> list = new ArrayList<>(callInfo.getDeviceList());
        list.remove(agentInfo.getDeviceId());
        list.remove(consultDevice.getDeviceId());

        callInfo.getNextCommands().add(new NextCommand(consultDevice.getDeviceId(), NextType.NEXT_CALL_BRIDGE, list.get(0)));
        cacheService.addCallInfo(callInfo);
    }
}
