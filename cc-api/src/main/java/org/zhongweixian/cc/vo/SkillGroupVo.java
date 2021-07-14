package org.zhongweixian.cc.vo;

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


    /**
     * 等级类型(1:全部,2:等于,3:>,4:<,5:介于)
     */
    @NotNull(message = "等级类型不能为空")
    private Integer rankAbout;

    /**
     * 介于的开始值
     */
    private Integer rankValueStart;

    /**
     * 等级值
     */
    private Integer rankValue;

    /**
     * 占用率
     */
    @NotNull(message = "占用率不能为空")
    private Integer rate;


    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Integer getRankAbout() {
        return rankAbout;
    }

    public void setRankAbout(Integer rankAbout) {
        this.rankAbout = rankAbout;
    }

    public Integer getRankValueStart() {
        return rankValueStart;
    }

    public void setRankValueStart(Integer rankValueStart) {
        this.rankValueStart = rankValueStart;
    }

    public Integer getRankValue() {
        return rankValue;
    }

    public void setRankValue(Integer rankValue) {
        this.rankValue = rankValue;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
