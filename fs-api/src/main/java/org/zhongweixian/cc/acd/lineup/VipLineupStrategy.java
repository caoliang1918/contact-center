package org.zhongweixian.cc.acd.lineup;

import org.cti.cc.po.CallInfo;
import org.cti.cc.strategy.LineupStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhongweixian.cc.util.Constants;

/**
 * Created by caoliang on 2021/8/2
 * <p>
 * vip排队优先
 */
public class VipLineupStrategy implements LineupStrategy {
    private Logger logger = LoggerFactory.getLogger(VipLineupStrategy.class);

    @Override
    public Long calculateLevel(CallInfo callInfo) {
        Long vipLevel = 0L;
        if (callInfo.getProcessData().containsKey(Constants.VIP_LEVEL)) {
            vipLevel = Long.parseLong(callInfo.getProcessData().get(Constants.VIP_LEVEL).toString());
            logger.info("callId:{} lineup of vipLevel:{}", callInfo.getCallId(), vipLevel);
        }
        return vipLevel - callInfo.getQueueStartTime();
    }
}
