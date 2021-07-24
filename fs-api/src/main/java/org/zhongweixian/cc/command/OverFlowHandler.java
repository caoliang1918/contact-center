package org.zhongweixian.cc.command;

import org.cti.cc.enums.CauseEnums;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.command.base.BaseHandler;

import java.util.PriorityQueue;

/**
 * Create by caoliang on 2020/12/3
 * <p>
 * 电话溢出
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
        if (groupOverFlow == null || groupOverFlow.getOverflowType() == null || groupOverFlow.getOverflowValue() == null) {
            logger.warn("没有有效的溢出策略 callId:{}, group:{}", callInfo.getCallId(), groupInfo.getName());
            callInfo.setHangupDir(3);
            callInfo.setHangupCause(CauseEnums.PARAMTER_ERROR.name());
            hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceId);
            return;
        }
        logger.info("group:{} handleType is overflow, overflowType:{}, overflowValue:{}, callId:{}", groupInfo.getName(), groupOverFlow.getOverflowType(), groupOverFlow.getOverflowValue(), callInfo.getCallId());
        callInfo.setOverflowCount(callInfo.getOverflowCount() + 1);

        /**
         * 溢出(1:group,2:ivr,3:vdn)
         */
        switch (groupOverFlow.getOverflowType()) {
            case 1:
                logger.info("callId:{} overflow to group:{}", callInfo.getCallId(), callInfo.getCallId());
                GroupInfo overFlowGroup = cacheService.getGroupInfo(Long.valueOf(groupOverFlow.getOverflowValue()));
                groupHandler.hander(deviceId, callInfo, overFlowGroup);
                break;

            case 2:
                logger.info("callId:{} overflow to ivr:{}", callInfo.getCallId(), callInfo.getCallId());
                transferIvrHandler.handler(callInfo, callInfo.getDeviceInfoMap().get(deviceId), Long.valueOf(groupOverFlow.getOverflowValue()));
                break;

            case 3:
                logger.info("callId:{} overflow to vdn:{}", callInfo.getCallId(), groupOverFlow.getOverflowValue());
                vdnHandler.hanlder(callInfo, callInfo.getDeviceInfoMap().get(deviceId), Long.valueOf(groupOverFlow.getOverflowValue()));
                break;

            default:
                break;
        }
    }
}
