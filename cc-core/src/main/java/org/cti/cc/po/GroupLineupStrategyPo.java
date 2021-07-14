package org.cti.cc.po;

import org.cti.cc.entity.GroupLineupStrategy;
import org.cti.cc.entity.GroupStrategyExp;

import java.util.List;

/**
 * Created by caoliang on 2020/11/25
 */
public class GroupLineupStrategyPo extends GroupLineupStrategy {

    /**
     * 坐席自定义策略
     */
    private List<GroupStrategyExp> strategyExpList;

    public List<GroupStrategyExp> getStrategyExpList() {
        return strategyExpList;
    }

    public void setStrategyExpList(List<GroupStrategyExp> strategyExpList) {
        this.strategyExpList = strategyExpList;
    }
}
