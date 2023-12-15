package com.voice9.core.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by caoliang on 2021/12/9
 */
public class SkillAgentVo {

    private Long id;

    private Long companyId;

    @NotNull(message = "坐席id不能为空")
    private Long agentId;

    @NotNull(message = "技能等级不能为空")
    private Integer rankValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Integer getRankValue() {
        return rankValue;
    }

    public void setRankValue(Integer rankValue) {
        this.rankValue = rankValue;
    }
}
