package org.voice9.cc.acd.assign;

import com.voice9.core.po.AgentInfo;
import com.voice9.core.strategy.AgentStrategy;

/**
 * Created by caoliang on 2021/8/3
 * <p>
 * 最少通话时长优先分配
 */
public class LeastTalkAssign implements AgentStrategy {
    @Override
    public Long calculateLevel(AgentInfo agentInfo) {
        return -agentInfo.getTotalTalkTime();
    }
}
