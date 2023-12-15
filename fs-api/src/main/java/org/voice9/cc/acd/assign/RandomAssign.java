package org.voice9.cc.acd.assign;

import org.apache.commons.lang3.RandomUtils;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.strategy.AgentStrategy;

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
