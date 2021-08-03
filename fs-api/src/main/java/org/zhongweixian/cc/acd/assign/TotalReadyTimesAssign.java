package org.zhongweixian.cc.acd.assign;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.strategy.AgentStrategy;

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
