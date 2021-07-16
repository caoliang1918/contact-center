package org.zhongweixian.cc.command;

import org.cti.cc.enums.CauseEnums;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.GroupInfo;
import org.cti.cc.po.GroupOverFlow;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.command.base.BaseHandler;

/**
 * Create by caoliang on 2020/12/3
 */

@Component
public class OverFlowHandler extends BaseHandler {

    public void overflow(CallInfo callInfo, String deviceId, Long groupId) {
        GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
        if (groupInfo == null) {
            callInfo.setHangupDir(3);
            callInfo.setHangupCause(CauseEnums.PARAMTER_ERROR.name());
            hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceId);
            return;
        }
        GroupOverFlow groupOverFlow = groupHandler.getEffectiveOverflow(groupInfo);
        if (groupOverFlow == null) {
            callInfo.setHangupDir(3);
            callInfo.setHangupCause(CauseEnums.PARAMTER_ERROR.name());
            hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceId);
            return;
        }
        logger.info("group:{} handleType is overflow, overflowType:{}, overflowValue:{}, callId:{}", groupInfo.getName(), groupOverFlow.getOverflowType(), groupOverFlow.getOverflowValue(), callInfo.getCallId());


    }
}
