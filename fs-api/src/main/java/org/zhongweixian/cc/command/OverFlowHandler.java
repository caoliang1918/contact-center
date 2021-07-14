package org.zhongweixian.cc.command;

import org.cti.cc.po.CallInfo;
import org.cti.cc.po.GroupInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.command.base.BaseHandler;

/**
 * Create by caoliang on 2020/12/3
 */

@Component
public class OverFlowHandler extends BaseHandler {

    public void overflow(CallInfo callInfo, String deviceId) {
        GroupInfo groupInfo = cacheService.getGroupInfo(callInfo.getGroupId());
//        logger.info("group:{} handleType is overflow, overflowType:{}, overflowValue:{}, callId:{}", groupInfo.getName(), groupOverflow.getOverflowType(), groupOverflow.getOverflowValue(), callInfo.getCallId());

    }
}
