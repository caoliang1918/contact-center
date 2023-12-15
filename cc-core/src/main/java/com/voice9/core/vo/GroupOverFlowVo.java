package com.voice9.core.vo;

import javax.validation.constraints.NotNull;

public class GroupOverFlowVo {

    @NotNull(message = "排队策略不能为空")
    private Long overFlowId;

    @NotNull(message = "策略等级不能为空")
    private Integer levelValue;

    public Long getOverFlowId() {
        return overFlowId;
    }

    public void setOverFlowId(Long overFlowId) {
        this.overFlowId = overFlowId;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }
}
