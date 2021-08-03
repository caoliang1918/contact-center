package org.zhongweixian.cc.acd.assign;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.strategy.AgentStrategy;

/**
 * Created by caoliang on 2021/8/3
 * <p>
 * 最少应答次数优先
 */
public class LeastAnswerAssign implements AgentStrategy {

    @Override
    public Long calculateLevel(AgentInfo agentInfo) {
        return -agentInfo.getTotalAnswerTimes();
    }
}
