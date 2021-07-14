package org.cti.cc.po;

import org.cti.cc.entity.Group;
import org.cti.cc.entity.GroupMemoryConfig;
import org.cti.cc.entity.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by caoliang on 2020/11/6
 */
public class GroupInfo extends Group {

    /**
     * 当前在线坐席
     */
    private List<String> onlineAgents = new ArrayList<>();

    /**
     * 所有技能
     */
    private List<Skill> skills;

    /**
     * 坐席的策略
     */
    private GroupLineupStrategyPo lineupStrategy;

    /**
     * 技能组溢出策略
     */
    private List<GroupOverflowPo> groupOverflows;

    /**
     * 主叫显号号码池
     */
    private List<String> callerDisplays;

    /**
     * 被叫显号号码池
     */
    private List<String> calledDisplays;

    /**
     * 坐席记忆配置
     */
    private GroupMemoryConfig groupMemoryConfig;

    public List<String> getOnlineAgents() {
        return onlineAgents;
    }

    public void setOnlineAgents(List<String> onlineAgents) {
        this.onlineAgents = onlineAgents;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public GroupLineupStrategyPo getLineupStrategy() {
        return lineupStrategy;
    }

    public void setLineupStrategy(GroupLineupStrategyPo lineupStrategy) {
        this.lineupStrategy = lineupStrategy;
    }

    public List<GroupOverflowPo> getGroupOverflows() {
        return groupOverflows;
    }

    public void setGroupOverflows(List<GroupOverflowPo> groupOverflows) {
        this.groupOverflows = groupOverflows;
    }

    public List<String> getCallerDisplays() {
        return callerDisplays;
    }

    public void setCallerDisplays(List<String> callerDisplays) {
        this.callerDisplays = callerDisplays;
    }

    public List<String> getCalledDisplays() {
        return calledDisplays;
    }

    public void setCalledDisplays(List<String> calledDisplays) {
        this.calledDisplays = calledDisplays;
    }

    public GroupMemoryConfig getGroupMemoryConfig() {
        return groupMemoryConfig;
    }

    public void setGroupMemoryConfig(GroupMemoryConfig groupMemoryConfig) {
        this.groupMemoryConfig = groupMemoryConfig;
    }
}
