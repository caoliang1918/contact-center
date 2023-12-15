package com.voice9.core.vo;

import com.voice9.core.po.AgentState;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by caoliang on 2021/9/30
 */
public class AgentPreset {

    /**
     * 1:当前生效 2:永久生效
     */
    @NotNull(message = "预设类型不能为空")
    @Range(min = 1 , max = 2, message = "预设字段错误")
    private Integer type;


    @NotNull(message = "预设状态不能为空")
    private AgentState agentState;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public AgentState getAgentState() {
        return agentState;
    }

    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
    }
}
