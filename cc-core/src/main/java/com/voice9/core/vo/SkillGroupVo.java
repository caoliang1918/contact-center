package com.voice9.core.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by caoliang on 2021/5/9
 */
public class SkillGroupVo {

    /**
     * 技能ID
     */
    @NotNull(message = "技能id不能为空")
    private Long skillId;

    @NotNull(message = "技能等级不能为空")
    private Integer levelValue;

    /**
     * 等级
     */
    @Range(min = 1, max = 100)
    private int rankValue;

    /**
     * 介于之间时，起始值
     */
    @Range(min = 1, max = 100)
    private int rankValueStart;

    /**
     * 0:全部; 1:小于或等于; 2:等于; 3:大于或等于; 4:大于
     */
    @NotNull(message = "等级类型不能为空")
    @Range(min = 0, max = 4, message = "等级类型错误")
    private Integer rankType;

    /**
     * 1:由低到高，2:由高到低
     */
    @NotNull(message = "匹配类型不能为空")
    @Range(min = 1, max = 2, message = "匹配策略错误")
    private Integer matchType;

    @NotNull(message = "占用率不能为空")
    @Range(min = 1, max = 100, message = "占用率在1-100之间")
    private Integer shareValue;


    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }

    public int getRankValue() {
        return rankValue;
    }

    public void setRankValue(int rankValue) {
        this.rankValue = rankValue;
    }

    public int getRankValueStart() {
        return rankValueStart;
    }

    public void setRankValueStart(int rankValueStart) {
        this.rankValueStart = rankValueStart;
    }

    public Integer getRankType() {
        return rankType;
    }

    public void setRankType(Integer rankType) {
        this.rankType = rankType;
    }

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public Integer getShareValue() {
        return shareValue;
    }

    public void setShareValue(Integer shareValue) {
        this.shareValue = shareValue;
    }
}
