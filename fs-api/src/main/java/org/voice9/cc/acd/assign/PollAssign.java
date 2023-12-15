package org.voice9.cc.acd.assign;

import com.voice9.core.po.AgentInfo;
import com.voice9.core.strategy.AgentStrategy;

/**
 * Created by caoliang on 2021/8/3
 * <p>
 * 坐席轮选
 */
public class PollAssign implements AgentStrategy {
    @Override
    public Long calculateLevel(AgentInfo agentInfo) {
        return -agentInfo.getServiceTime();
    }
}
