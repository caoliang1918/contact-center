package org.voice9.cc.acd.assign;

import com.voice9.core.po.AgentInfo;
import com.voice9.core.strategy.AgentStrategy;

/**
 * Created by caoliang on 2021/8/3
 * <p>
 * 空闲次数最多优先
 */
public class TotalReadyTimesAssign implements AgentStrategy {
    @Override
    public Long calculateLevel(AgentInfo agentInfo) {
        return agentInfo.getReadyTimes();
    }
}
