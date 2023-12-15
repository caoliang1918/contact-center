package org.voice9.cc.command;

import com.voice9.core.po.CallInfo;
import com.voice9.core.po.GroupInfo;
import com.voice9.core.po.GroupOverflowPo;
import org.springframework.stereotype.Component;
import org.voice9.cc.command.base.BaseHandler;

/**
 * Create by caoliang on 2020/12/3
 * <p>
 * 电话溢出
 */
@Component
public class OverFlowHandler extends BaseHandler {

    public void overflow(CallInfo callInfo, String deviceId, GroupOverflowPo groupOverFlow) {
        logger.info("callId:{} handleType is overflow, overflowType:{}, overflowValue:{}", callInfo.getCallId(), groupOverFlow.getOverflowType(), groupOverFlow.getOverflowValue());
        callInfo.setOverflowCount(callInfo.getOverflowCount() + 1);

        /**
         * 溢出(1:group,2:ivr,3:vdn)
         */
        switch (groupOverFlow.getOverflowType()) {
            case 1:
                logger.info("callId:{} overflow to group:{}", callInfo.getCallId(), callInfo.getCallId());
                GroupInfo overFlowGroup = cacheService.getGroupInfo(Long.valueOf(groupOverFlow.getOverflowValue()));
                groupHandler.hander(callInfo, overFlowGroup, deviceId);
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
