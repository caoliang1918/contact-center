package org.zhongweixian.cc.acd.assign;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.strategy.AgentStrategy;

/**
 * Created by caoliang on 2021/8/3
 * <p>
 * 当前最长空闲时间
 */
public class LongReadyAssign implements AgentStrategy {

    @Override
    public Long calculateLevel(AgentInfo agentInfo) {
        return -agentInfo.getStateTime();
    }
}
