package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 技能组技能表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class SkillGroup implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     * 创建时间
     */
    private Long cts;

    /**
     * 修改时间
     */
    private Long uts;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 技能ID
     */
    private Long skillId;

    /**
     * 技能组ID
     */
    private Long groupId;

    /**
     * 优先级
     */
    private Integer levelValue;

    /**
     * 等级类型(1:全部,2:等于,3:>,4:<,5:介于)
     */
    private Integer rankType;

    /**
     * 介于的开始值
     */
    private int rankValueStart;

    /**
     * 等级值
     */
    private int rankValue;

    /**
     * 匹配规则(1:低到高,2:高到低)
     */
    private Integer matchType;

    /**
     * 占用率
     */
    private Integer shareValue;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCts() {
        return cts;
    }

    public void setCts(Long cts) {
        this.cts = cts;
    }

    public Long getUts() {
        return uts;
    }

    public void setUts(Long uts) {
        this.uts = uts;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }

    public Integer getRankType() {
        return rankType;
    }

    public void setRankType(Integer rankType) {
        this.rankType = rankType;
    }

    public int getRankValueStart() {
        return rankValueStart;
    }

    public void setRankValueStart(int rankValueStart) {
        this.rankValueStart = rankValueStart;
    }

    public int getRankValue() {
        return rankValue;
    }

    public void setRankValue(int rankValue) {
        this.rankValue = rankValue;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}