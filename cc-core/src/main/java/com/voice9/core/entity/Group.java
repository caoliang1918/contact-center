package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 技能组表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class Group implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     * 新增时间
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
     * 技能组名称
     */
    private String name;

    /**
     * 话后自动空闲时长
     */
    private Integer afterInterval;

    /**
     * 主叫显号号码池
     */
    private Long callerDisplayId;

    /**
     * 被叫显号号码池
     */
    private Long calledDisplayId;

    /**
     * 0:不录音,1:振铃录音,2:接通录音
     */
    private Integer recordType;

    /**
     * 技能组优先级
     */
    private Integer levelValue;

    /**
     * 
     */
    private Long ttsEngine;

    /**
     * 转坐席时播放内容
     */
    private String playContent;

    /**
     * 转服务评价(0:否,1:是)
     */
    private Long evaluate;

    /**
     * 排队音
     */
    private Long queuePlay;

    /**
     * 转接提示音
     */
    private Long transferPlay;

    /**
     * 外呼呼叫超时时间
     */
    private Integer callTimeOut;

    /**
     * 技能组类型
     */
    private Integer groupType;

    /**
     * 0:不播放排队位置,1:播放排队位置
     */
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
     * 扩展1
     */
    private String ext1;

    /**
     * 扩展2
     */
    private String ext2;

    /**
     * 扩展3
     */
    private String ext3;

    /**
     * 扩展4
     */
    private String ext4;

    /**
     * 扩展5
     */
    private String ext5;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Long getCalledDisplayId() {
        return calledDisplayId;
    }

    public void setCalledDisplayId(Long calledDisplayId) {
        this.calledDisplayId = calledDisplayId;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }

    public Long getTtsEngine() {
        return ttsEngine;
    }

    public void setTtsEngine(Long ttsEngine) {
        this.ttsEngine = ttsEngine;
    }

    public String getPlayContent() {
        return playContent;
    }

    public void setPlayContent(String playContent) {
        this.playContent = playContent == null ? null : playContent.trim();
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

    public Integer getCallTimeOut() {
        return callTimeOut;
    }

    public void setCallTimeOut(Integer callTimeOut) {
        this.callTimeOut = callTimeOut;
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
        this.notifyContent = notifyContent == null ? null : notifyContent.trim();
    }

    public Integer getCallMemory() {
        return callMemory;
    }

    public void setCallMemory(Integer callMemory) {
        this.callMemory = callMemory;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cts=").append(cts);
        sb.append(", uts=").append(uts);
        sb.append(", companyId=").append(companyId);
        sb.append(", name=").append(name);
        sb.append(", afterInterval=").append(afterInterval);
        sb.append(", callerDisplayId=").append(callerDisplayId);
        sb.append(", calledDisplayId=").append(calledDisplayId);
        sb.append(", recordType=").append(recordType);
        sb.append(", levelValue=").append(levelValue);
        sb.append(", ttsEngine=").append(ttsEngine);
        sb.append(", playContent=").append(playContent);
        sb.append(", evaluate=").append(evaluate);
        sb.append(", queuePlay=").append(queuePlay);
        sb.append(", transferPlay=").append(transferPlay);
        sb.append(", groupType=").append(groupType);
        sb.append(", notifyPosition=").append(notifyPosition);
        sb.append(", notifyRate=").append(notifyRate);
        sb.append(", notifyContent=").append(notifyContent);
        sb.append(", callMemory=").append(callMemory);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", ext3=").append(ext3);
        sb.append(", ext4=").append(ext4);
        sb.append(", ext5=").append(ext5);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Group other = (Group) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAfterInterval() == null ? other.getAfterInterval() == null : this.getAfterInterval().equals(other.getAfterInterval()))
            && (this.getCallerDisplayId() == null ? other.getCallerDisplayId() == null : this.getCallerDisplayId().equals(other.getCallerDisplayId()))
            && (this.getCalledDisplayId() == null ? other.getCalledDisplayId() == null : this.getCalledDisplayId().equals(other.getCalledDisplayId()))
            && (this.getRecordType() == null ? other.getRecordType() == null : this.getRecordType().equals(other.getRecordType()))
            && (this.getLevelValue() == null ? other.getLevelValue() == null : this.getLevelValue().equals(other.getLevelValue()))
            && (this.getTtsEngine() == null ? other.getTtsEngine() == null : this.getTtsEngine().equals(other.getTtsEngine()))
            && (this.getPlayContent() == null ? other.getPlayContent() == null : this.getPlayContent().equals(other.getPlayContent()))
            && (this.getEvaluate() == null ? other.getEvaluate() == null : this.getEvaluate().equals(other.getEvaluate()))
            && (this.getQueuePlay() == null ? other.getQueuePlay() == null : this.getQueuePlay().equals(other.getQueuePlay()))
            && (this.getTransferPlay() == null ? other.getTransferPlay() == null : this.getTransferPlay().equals(other.getTransferPlay()))
            && (this.getGroupType() == null ? other.getGroupType() == null : this.getGroupType().equals(other.getGroupType()))
            && (this.getNotifyPosition() == null ? other.getNotifyPosition() == null : this.getNotifyPosition().equals(other.getNotifyPosition()))
            && (this.getNotifyRate() == null ? other.getNotifyRate() == null : this.getNotifyRate().equals(other.getNotifyRate()))
            && (this.getNotifyContent() == null ? other.getNotifyContent() == null : this.getNotifyContent().equals(other.getNotifyContent()))
            && (this.getCallMemory() == null ? other.getCallMemory() == null : this.getCallMemory().equals(other.getCallMemory()))
            && (this.getExt1() == null ? other.getExt1() == null : this.getExt1().equals(other.getExt1()))
            && (this.getExt2() == null ? other.getExt2() == null : this.getExt2().equals(other.getExt2()))
            && (this.getExt3() == null ? other.getExt3() == null : this.getExt3().equals(other.getExt3()))
            && (this.getExt4() == null ? other.getExt4() == null : this.getExt4().equals(other.getExt4()))
            && (this.getExt5() == null ? other.getExt5() == null : this.getExt5().equals(other.getExt5()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAfterInterval() == null) ? 0 : getAfterInterval().hashCode());
        result = prime * result + ((getCallerDisplayId() == null) ? 0 : getCallerDisplayId().hashCode());
        result = prime * result + ((getCalledDisplayId() == null) ? 0 : getCalledDisplayId().hashCode());
        result = prime * result + ((getRecordType() == null) ? 0 : getRecordType().hashCode());
        result = prime * result + ((getLevelValue() == null) ? 0 : getLevelValue().hashCode());
        result = prime * result + ((getTtsEngine() == null) ? 0 : getTtsEngine().hashCode());
        result = prime * result + ((getPlayContent() == null) ? 0 : getPlayContent().hashCode());
        result = prime * result + ((getEvaluate() == null) ? 0 : getEvaluate().hashCode());
        result = prime * result + ((getQueuePlay() == null) ? 0 : getQueuePlay().hashCode());
        result = prime * result + ((getTransferPlay() == null) ? 0 : getTransferPlay().hashCode());
        result = prime * result + ((getGroupType() == null) ? 0 : getGroupType().hashCode());
        result = prime * result + ((getNotifyPosition() == null) ? 0 : getNotifyPosition().hashCode());
        result = prime * result + ((getNotifyRate() == null) ? 0 : getNotifyRate().hashCode());
        result = prime * result + ((getNotifyContent() == null) ? 0 : getNotifyContent().hashCode());
        result = prime * result + ((getCallMemory() == null) ? 0 : getCallMemory().hashCode());
        result = prime * result + ((getExt1() == null) ? 0 : getExt1().hashCode());
        result = prime * result + ((getExt2() == null) ? 0 : getExt2().hashCode());
        result = prime * result + ((getExt3() == null) ? 0 : getExt3().hashCode());
        result = prime * result + ((getExt4() == null) ? 0 : getExt4().hashCode());
        result = prime * result + ((getExt5() == null) ? 0 : getExt5().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}