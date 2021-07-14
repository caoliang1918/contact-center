package org.cti.cc.entity;

import java.io.Serializable;

/**
 * 话单表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class CallLog implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 落单时间
     */
    private Long cts;

    /**
     * 修改时间
     */
    private Long uts;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 话单id
     */
    private Long callId;

    /**
     * 主叫显号
     */
    private String callerDisplay;

    /**
     * 主叫
     */
    private String caller;

    /**
     * 被叫显号
     */
    private String calledDisplay;

    /**
     * 被叫
     */
    private String called;

    /**
     * 坐席
     */
    private String agentKey;

    /**
     * 技能组
     */
    private Long groupId;

    /**
     * 1:sip号,2:webrtc,3:手机
     */
    private Integer loginType;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * ivr
     */
    private Long ivrId;

    /**
     * 机器人id
     */
    private Long botId;

    /**
     * 呼叫开始时间
     */
    private Long callTime;

    /**
     * 接听时间
     */
    private Long answerTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 呼叫类型
     */
    private String callType;

    /**
     * 呼叫方向
     */
    private String direction;

    /**
     * 通话标识(0:接通,1:坐席未接用户未接,2:坐席接通用户未接通,3:用户接通坐席未接通)
     */
    private Integer answerFlag;

    /**
     * 累计等待时长
     */
    private Long waitTime;

    /**
     * 应答设备数
     */
    private Integer answerCount;

    /**
     * 挂机方向(1:主叫挂机,2:被叫挂机,3:系统挂机)
     */
    private Integer hangupDir;

    /**
     * 挂机原因
     */
    private String hangupCause;

    /**
     * 媒体服务器
     */
    private String media;

    /**
     * 录音地址
     */
    private String record;

    /**
     * 通话时长(秒)
     */
    private Long talkTime;

    /**
     * 通话随路数据(2048)
     */
    private String followData;

    /**
     * 扩展1
     */
    private String uuid1;

    /**
     * 扩展2
     */
    private String uuid2;

    /**
     * 扩展3
     */
    private String ext1;

    /**
     * 扩展4
     */
    private String ext2;

    /**
     * 扩展5
     */
    private String ext3;

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

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getCallerDisplay() {
        return callerDisplay;
    }

    public void setCallerDisplay(String callerDisplay) {
        this.callerDisplay = callerDisplay == null ? null : callerDisplay.trim();
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller == null ? null : caller.trim();
    }

    public String getCalledDisplay() {
        return calledDisplay;
    }

    public void setCalledDisplay(String calledDisplay) {
        this.calledDisplay = calledDisplay == null ? null : calledDisplay.trim();
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called == null ? null : called.trim();
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey == null ? null : agentKey.trim();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getIvrId() {
        return ivrId;
    }

    public void setIvrId(Long ivrId) {
        this.ivrId = ivrId;
    }

    public Long getBotId() {
        return botId;
    }

    public void setBotId(Long botId) {
        this.botId = botId;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType == null ? null : callType.trim();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public Integer getAnswerFlag() {
        return answerFlag;
    }

    public void setAnswerFlag(Integer answerFlag) {
        this.answerFlag = answerFlag;
    }

    public Long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Long waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getHangupDir() {
        return hangupDir;
    }

    public void setHangupDir(Integer hangupDir) {
        this.hangupDir = hangupDir;
    }

    public String getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause == null ? null : hangupCause.trim();
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media == null ? null : media.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public Long getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }

    public String getFollowData() {
        return followData;
    }

    public void setFollowData(String followData) {
        this.followData = followData == null ? null : followData.trim();
    }

    public String getUuid1() {
        return uuid1;
    }

    public void setUuid1(String uuid1) {
        this.uuid1 = uuid1 == null ? null : uuid1.trim();
    }

    public String getUuid2() {
        return uuid2;
    }

    public void setUuid2(String uuid2) {
        this.uuid2 = uuid2 == null ? null : uuid2.trim();
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
        sb.append(", callId=").append(callId);
        sb.append(", callerDisplay=").append(callerDisplay);
        sb.append(", caller=").append(caller);
        sb.append(", calledDisplay=").append(calledDisplay);
        sb.append(", called=").append(called);
        sb.append(", agentKey=").append(agentKey);
        sb.append(", groupId=").append(groupId);
        sb.append(", loginType=").append(loginType);
        sb.append(", taskId=").append(taskId);
        sb.append(", ivrId=").append(ivrId);
        sb.append(", botId=").append(botId);
        sb.append(", callTime=").append(callTime);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", callType=").append(callType);
        sb.append(", direction=").append(direction);
        sb.append(", answerFlag=").append(answerFlag);
        sb.append(", waitTime=").append(waitTime);
        sb.append(", answerCount=").append(answerCount);
        sb.append(", hangupDir=").append(hangupDir);
        sb.append(", hangupCause=").append(hangupCause);
        sb.append(", media=").append(media);
        sb.append(", record=").append(record);
        sb.append(", talkTime=").append(talkTime);
        sb.append(", followData=").append(followData);
        sb.append(", uuid1=").append(uuid1);
        sb.append(", uuid2=").append(uuid2);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", ext3=").append(ext3);
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
        CallLog other = (CallLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCallId() == null ? other.getCallId() == null : this.getCallId().equals(other.getCallId()))
            && (this.getCallerDisplay() == null ? other.getCallerDisplay() == null : this.getCallerDisplay().equals(other.getCallerDisplay()))
            && (this.getCaller() == null ? other.getCaller() == null : this.getCaller().equals(other.getCaller()))
            && (this.getCalledDisplay() == null ? other.getCalledDisplay() == null : this.getCalledDisplay().equals(other.getCalledDisplay()))
            && (this.getCalled() == null ? other.getCalled() == null : this.getCalled().equals(other.getCalled()))
            && (this.getAgentKey() == null ? other.getAgentKey() == null : this.getAgentKey().equals(other.getAgentKey()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getLoginType() == null ? other.getLoginType() == null : this.getLoginType().equals(other.getLoginType()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getIvrId() == null ? other.getIvrId() == null : this.getIvrId().equals(other.getIvrId()))
            && (this.getBotId() == null ? other.getBotId() == null : this.getBotId().equals(other.getBotId()))
            && (this.getCallTime() == null ? other.getCallTime() == null : this.getCallTime().equals(other.getCallTime()))
            && (this.getAnswerTime() == null ? other.getAnswerTime() == null : this.getAnswerTime().equals(other.getAnswerTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getCallType() == null ? other.getCallType() == null : this.getCallType().equals(other.getCallType()))
            && (this.getDirection() == null ? other.getDirection() == null : this.getDirection().equals(other.getDirection()))
            && (this.getAnswerFlag() == null ? other.getAnswerFlag() == null : this.getAnswerFlag().equals(other.getAnswerFlag()))
            && (this.getWaitTime() == null ? other.getWaitTime() == null : this.getWaitTime().equals(other.getWaitTime()))
            && (this.getAnswerCount() == null ? other.getAnswerCount() == null : this.getAnswerCount().equals(other.getAnswerCount()))
            && (this.getHangupDir() == null ? other.getHangupDir() == null : this.getHangupDir().equals(other.getHangupDir()))
            && (this.getHangupCause() == null ? other.getHangupCause() == null : this.getHangupCause().equals(other.getHangupCause()))
            && (this.getMedia() == null ? other.getMedia() == null : this.getMedia().equals(other.getMedia()))
            && (this.getRecord() == null ? other.getRecord() == null : this.getRecord().equals(other.getRecord()))
            && (this.getTalkTime() == null ? other.getTalkTime() == null : this.getTalkTime().equals(other.getTalkTime()))
            && (this.getFollowData() == null ? other.getFollowData() == null : this.getFollowData().equals(other.getFollowData()))
            && (this.getUuid1() == null ? other.getUuid1() == null : this.getUuid1().equals(other.getUuid1()))
            && (this.getUuid2() == null ? other.getUuid2() == null : this.getUuid2().equals(other.getUuid2()))
            && (this.getExt1() == null ? other.getExt1() == null : this.getExt1().equals(other.getExt1()))
            && (this.getExt2() == null ? other.getExt2() == null : this.getExt2().equals(other.getExt2()))
            && (this.getExt3() == null ? other.getExt3() == null : this.getExt3().equals(other.getExt3()))
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
        result = prime * result + ((getCallId() == null) ? 0 : getCallId().hashCode());
        result = prime * result + ((getCallerDisplay() == null) ? 0 : getCallerDisplay().hashCode());
        result = prime * result + ((getCaller() == null) ? 0 : getCaller().hashCode());
        result = prime * result + ((getCalledDisplay() == null) ? 0 : getCalledDisplay().hashCode());
        result = prime * result + ((getCalled() == null) ? 0 : getCalled().hashCode());
        result = prime * result + ((getAgentKey() == null) ? 0 : getAgentKey().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getLoginType() == null) ? 0 : getLoginType().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getIvrId() == null) ? 0 : getIvrId().hashCode());
        result = prime * result + ((getBotId() == null) ? 0 : getBotId().hashCode());
        result = prime * result + ((getCallTime() == null) ? 0 : getCallTime().hashCode());
        result = prime * result + ((getAnswerTime() == null) ? 0 : getAnswerTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getCallType() == null) ? 0 : getCallType().hashCode());
        result = prime * result + ((getDirection() == null) ? 0 : getDirection().hashCode());
        result = prime * result + ((getAnswerFlag() == null) ? 0 : getAnswerFlag().hashCode());
        result = prime * result + ((getWaitTime() == null) ? 0 : getWaitTime().hashCode());
        result = prime * result + ((getAnswerCount() == null) ? 0 : getAnswerCount().hashCode());
        result = prime * result + ((getHangupDir() == null) ? 0 : getHangupDir().hashCode());
        result = prime * result + ((getHangupCause() == null) ? 0 : getHangupCause().hashCode());
        result = prime * result + ((getMedia() == null) ? 0 : getMedia().hashCode());
        result = prime * result + ((getRecord() == null) ? 0 : getRecord().hashCode());
        result = prime * result + ((getTalkTime() == null) ? 0 : getTalkTime().hashCode());
        result = prime * result + ((getFollowData() == null) ? 0 : getFollowData().hashCode());
        result = prime * result + ((getUuid1() == null) ? 0 : getUuid1().hashCode());
        result = prime * result + ((getUuid2() == null) ? 0 : getUuid2().hashCode());
        result = prime * result + ((getExt1() == null) ? 0 : getExt1().hashCode());
        result = prime * result + ((getExt2() == null) ? 0 : getExt2().hashCode());
        result = prime * result + ((getExt3() == null) ? 0 : getExt3().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}