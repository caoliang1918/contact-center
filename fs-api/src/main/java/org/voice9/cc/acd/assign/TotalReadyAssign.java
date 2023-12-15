package org.voice9.cc.acd.assign;

import com.voice9.core.po.AgentInfo;
import com.voice9.core.strategy.AgentStrategy;

/**
 * Created by caoliang on 2021/8/3
 *
 * 累计空闲最长时间，不包含当前的空闲时间
 */
public class TotalReadyAssign implements AgentStrategy {

    @Override
    public Long calculateLevel(AgentInfo agentInfo) {
        return agentInfo.getTotalReadyTime();
    }
}
