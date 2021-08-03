package org.zhongweixian.cc.acd.assign;

import org.apache.commons.lang3.RandomUtils;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.strategy.AgentStrategy;

/**
 * Created by caoliang on 2021/8/3
 * <p>
 * 随机分配
 */
public class RandomAssign implements AgentStrategy {

    @Override
    public Long calculateLevel(AgentInfo agentInfo) {
        return RandomUtils.nextLong();
    }
}
