package com.voice9.core.po;

import com.voice9.core.entity.Group;
import com.voice9.core.entity.GroupMemoryConfig;
import com.voice9.core.entity.Skill;

import java.util.ArrayList;
import java.util.List;

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
     * 技能组中坐席分配策略
     */
    private GroupAgentStrategyPo groupAgentStrategyPo;

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

    /**
     * 最大空闲时长
     */
    private Integer maxWaitTime = 0;

    /**
     * 最大空闲时长
     */
    private Integer maxReadyTime = 0;

    /**
     * 呼入总数
     */
    private Integer callInTotal = 0;

    /**
     * 呼入应答数
     */
    private Integer callInAnswer = 0;

    /**
     * 呼入最后的分配时间
     */
    private Long lastServiceTime;


    public List<String> getOnlineAgents() {
        return onlineAgents;
    }

    public void setOnlineAgents(List<String> onlineAgents) {
        this.onlineAgents = onlineAgents;
    }

    public GroupAgentStrategyPo getGroupAgentStrategyPo() {
        return groupAgentStrategyPo;
    }

    public void setGroupAgentStrategyPo(GroupAgentStrategyPo groupAgentStrategyPo) {
        this.groupAgentStrategyPo = groupAgentStrategyPo;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
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

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public Integer getMaxReadyTime() {
        return maxReadyTime;
    }

    public void setMaxReadyTime(Integer maxReadyTime) {
        this.maxReadyTime = maxReadyTime;
    }

    public Integer getCallInTotal() {
        return callInTotal;
    }

    public void setCallInTotal(Integer callInTotal) {
        this.callInTotal = callInTotal;
    }

    public Integer getCallInAnswer() {
        return callInAnswer;
    }

    public void setCallInAnswer(Integer callInAnswer) {
        this.callInAnswer = callInAnswer;
    }

    public Long getLastServiceTime() {
        return lastServiceTime;
    }

    public void setLastServiceTime(Long lastServiceTime) {
        this.lastServiceTime = lastServiceTime;
    }
}
