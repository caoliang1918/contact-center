package com.voice9.core.po;

import com.voice9.core.entity.GroupAgentStrategy;
import com.voice9.core.entity.GroupStrategyExp;
import com.voice9.core.strategy.AgentStrategy;

import java.util.List;

/**
 * Created by caoliang on 2020/11/25
 */
public class GroupAgentStrategyPo extends GroupAgentStrategy {

    /**
     * 坐席策略接口
     */
    private AgentStrategy agentStrategy;

    /**
     * 坐席自定义策略
     */
    private List<GroupStrategyExp> strategyExpList;

    public AgentStrategy getAgentStrategy() {
        return agentStrategy;
    }

    public void setAgentStrategy(AgentStrategy agentStrategy) {
        this.agentStrategy = agentStrategy;
    }

    public List<GroupStrategyExp> getStrategyExpList() {
        return strategyExpList;
    }

    public void setStrategyExpList(List<GroupStrategyExp> strategyExpList) {
        this.strategyExpList = strategyExpList;
    }
}
