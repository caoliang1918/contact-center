package org.voice9.cc.acd.lineup;

import com.voice9.core.po.CallInfo;
import com.voice9.core.strategy.LineupStrategy;

/**
 * Created by caoliang on 2021/8/2
 * <p>
 * 默认按照进技能组时间，时间越小转坐席越早
 */
public class DefaultLineupStrategy implements LineupStrategy {

    @Override
    public Long calculateLevel(CallInfo callInfo) {
        return -callInfo.getQueueStartTime();
    }
}
