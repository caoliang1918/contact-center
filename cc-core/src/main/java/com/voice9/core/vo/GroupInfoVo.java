package com.voice9.core.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class GroupInfoVo {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 技能组名称
     */
    @NotBlank(message = "技能组名称不能为空")
    @Size(min = 2, max = 16, message = "技能组名称必须在2-16字符")
    private String name;

    /**
     * 话后自动空闲时长
     */
    @Range(min = 0, max = 100, message = "话后时长设置错误")
    private Integer afterInterval;

    /**
     * 主叫显号号码池
     */
    private Long callerDisplayId;

    /**
     * 技能组优先级
     */
    private Integer levelValue;

    /**
     * 被叫显号号码池
     */
    private Long calledDisplayId;

    /**
     * 播放工号(0:不播放,1:中文,2:英文)
     */
    @Range(min = 0, max = 2, message = "播放工号设置错误")
    private Integer ttsPlay;

    /**
     * 播放内容
     */
    private String ttsContent;

    /**
     * 转服务评价(0:否,1:是)
     */
    @NotNull(message = "服务评价不能为空")
    @Range(min = 0, max = 1, message = "转服务评价设置错误")
    private Long evaluate;

    /**
     * 排队音
     */
    @NotNull(message = "排队音不能为空")
    private Long queuePlay;

    /**
     * 转接提示音
     */
    @NotNull(message = "转接坐席音不能为空")
    private Long transferPlay;

    /**
     * 技能组类型
     */
    @NotNull(message = "技能组类型不能为空")
    @Range(min = 1, max = 2, message = "技能组类型设置错误")
    private Integer groupType;

    /**
     * 0:不播放排队位置,1:播放排队位置
     */
    @Range(min = 0, max = 1, message = "技能组播放排队位置设置错误")
    private Integer notifyPosition;

    /**
     * 频次
     */
    private Integer notifyRate;

    /**
     * 您前面还有$位用户在等待
     */
    private String notifyContent;

    /**
     * 主叫记忆(1:开启,0:不开启)
     */
    private Integer callMemory;


    /**
     * 技能组技能
     */
    @NotNull(message = "技能组技能不能为空")
    private List<SkillGroupVo> skillList;

    /**
     * 技能组溢出策略
     */
    @NotNull(message = "技能组溢出策略不能为空")
    private List<GroupOverFlowVo> groupOverflows;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAfterInterval() {
        return afterInterval;
    }

    public void setAfterInterval(Integer afterInterval) {
        this.afterInterval = afterInterval;
    }

    public Long getCallerDisplayId() {
        return callerDisplayId;
    }

    public void setCallerDisplayId(Long callerDisplayId) {
        this.callerDisplayId = callerDisplayId;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }

    public Long getCalledDisplayId() {
        return calledDisplayId;
    }

    public void setCalledDisplayId(Long calledDisplayId) {
        this.calledDisplayId = calledDisplayId;
    }

    public Integer getTtsPlay() {
        return ttsPlay;
    }

    public void setTtsPlay(Integer ttsPlay) {
        this.ttsPlay = ttsPlay;
    }

    public String getTtsContent() {
        return ttsContent;
    }

    public void setTtsContent(String ttsContent) {
        this.ttsContent = ttsContent;
    }

    public Long getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Long evaluate) {
        this.evaluate = evaluate;
    }

    public Long getQueuePlay() {
        return queuePlay;
    }

    public void setQueuePlay(Long queuePlay) {
        this.queuePlay = queuePlay;
    }

    public Long getTransferPlay() {
        return transferPlay;
    }

    public void setTransferPlay(Long transferPlay) {
        this.transferPlay = transferPlay;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getNotifyPosition() {
        return notifyPosition;
    }

    public void setNotifyPosition(Integer notifyPosition) {
        this.notifyPosition = notifyPosition;
    }

    public Integer getNotifyRate() {
        return notifyRate;
    }

    public void setNotifyRate(Integer notifyRate) {
        this.notifyRate = notifyRate;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
    }

    public Integer getCallMemory() {
        return callMemory;
    }

    public void setCallMemory(Integer callMemory) {
        this.callMemory = callMemory;
    }

    public List<SkillGroupVo> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillGroupVo> skillList) {
        this.skillList = skillList;
    }

    public List<GroupOverFlowVo> getGroupOverflows() {
        return groupOverflows;
    }

    public void setGroupOverflows(List<GroupOverFlowVo> groupOverflows) {
        this.groupOverflows = groupOverflows;
    }
}
