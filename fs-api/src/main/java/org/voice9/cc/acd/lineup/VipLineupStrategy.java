package org.voice9.cc.acd.lineup;

import com.voice9.core.constant.Constant;
import com.voice9.core.po.CallInfo;
import com.voice9.core.strategy.LineupStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        if (callInfo.getProcessData().containsKey(Constant.VIP_LEVEL)) {
            vipLevel = Long.parseLong(callInfo.getProcessData().get(Constant.VIP_LEVEL).toString());
            logger.info("callId:{} lineup of vipLevel:{}", callInfo.getCallId(), vipLevel);
        }
        return vipLevel - callInfo.getQueueStartTime();
    }
}
