package com.voice9.api.vo;

import javax.validation.constraints.NotNull;

public class GroupOverFlowVo {

    @NotNull(message = "排队策略不能为空")
    private Long id;

    @NotNull(message = "策略等级不能为空")
    private Integer levelValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }
}
