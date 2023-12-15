package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 话单明细表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class CallDevice implements Serializable {
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
     * 租户id
     */
    private Long companyId;

    /**
     * 通话ID
     */
    private Long callId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 坐席
     */
    private String agentKey;

    /**
     * 坐席名称
     */
    private String agentName;

    /**
     * 1:坐席,2:客户,3:外线
     */
    private Integer deviceType;

    /**
     * 1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插,8:耳语
     */
    private Integer cdrType;

    /**
     * 咨询或者转接来源
     */
    private String fromAgent;

    /**
     * 主叫
     */
    private String caller;

    /**
     * 被叫
     */
    private String called;

    /**
     * 显号
     */
    private String display;

    /**
     * 被叫归属地
     */
    private String calledLocation;

    /**
     * 被叫归属地
     */
    private String callerLocation;

    /**
     * 呼叫开始时间
     */
    private Long callTime;

    /**
     * 振铃开始时间
     */
    private Long ringStartTime;

    /**
     * 振铃结束时间
     */
    private Long ringEndTime;

    /**
     * 接通时间
     */
    private Long answerTime;

    /**
     * 桥接时间
     */
    private Long bridgeTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 通话时长
     */
    private Long talkTime;

    /**
     * 录音开始时间
     */
    private Long recordStartTime;

    /**
     * 录音时长
     */
    private Long recordTime;

    /**
     * 信令协议(tcp/udp)
     */
    private String sipProtocol;

    /**
     * 录音地址
     */
    private String record;

    /**
     * 备用录音地址
     */
    private String record2;

    /**
     * 备用录音地址
     */
    private String record3;

    /**
     * 呼叫地址
     */
    private String channelName;

    /**
     * 挂机原因
     */
    private String hangupCause;

    /**
     * 回铃音识别
     */
    private String ringCause;

    /**
     * sip状态
     */
    private String sipStatus;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 状态
     */
    private Integer status;

    /**
     *
     */
    private String month;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey == null ? null : agentKey.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getCdrType() {
        return cdrType;
    }

    public void setCdrType(Integer cdrType) {
        this.cdrType = cdrType;
    }

    public String getFromAgent() {
        return fromAgent;
    }

    public void setFromAgent(String fromAgent) {
        this.fromAgent = fromAgent;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller == null ? null : caller.trim();
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called == null ? null : called.trim();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display == null ? null : display.trim();
    }

    public String getCalledLocation() {
        return calledLocation;
    }

    public void setCalledLocation(String calledLocation) {
        this.calledLocation = calledLocation == null ? null : calledLocation.trim();
    }

    public String getCallerLocation() {
        return callerLocation;
    }

    public void setCallerLocation(String callerLocation) {
        this.callerLocation = callerLocation == null ? null : callerLocation.trim();
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public Long getRingStartTime() {
        return ringStartTime;
    }

    public void setRingStartTime(Long ringStartTime) {
        this.ringStartTime = ringStartTime;
    }

    public Long getRingEndTime() {
        return ringEndTime;
    }

    public void setRingEndTime(Long ringEndTime) {
        this.ringEndTime = ringEndTime;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Long getBridgeTime() {
        return bridgeTime;
    }

    public void setBridgeTime(Long bridgeTime) {
        this.bridgeTime = bridgeTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }

    public Long getRecordStartTime() {
        return recordStartTime;
    }

    public void setRecordStartTime(Long recordStartTime) {
        this.recordStartTime = recordStartTime;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    public String getSipProtocol() {
        return sipProtocol;
    }

    public void setSipProtocol(String sipProtocol) {
        this.sipProtocol = sipProtocol == null ? null : sipProtocol.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getRecord2() {
        return record2;
    }

    public void setRecord2(String record2) {
        this.record2 = record2 == null ? null : record2.trim();
    }

    public String getRecord3() {
        return record3;
    }

    public void setRecord3(String record3) {
        this.record3 = record3 == null ? null : record3.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause == null ? null : hangupCause.trim();
    }

    public String getRingCause() {
        return ringCause;
    }

    public void setRingCause(String ringCause) {
        this.ringCause = ringCause == null ? null : ringCause.trim();
    }

    public String getSipStatus() {
        return sipStatus;
    }

    public void setSipStatus(String sipStatus) {
        this.sipStatus = sipStatus == null ? null : sipStatus.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
        sb.append(", callId=").append(callId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", agentKey=").append(agentKey);
        sb.append(", agentName=").append(agentName);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", cdrType=").append(cdrType);
        sb.append(", caller=").append(caller);
        sb.append(", called=").append(called);
        sb.append(", display=").append(display);
        sb.append(", calledLocation=").append(calledLocation);
        sb.append(", callerLocation=").append(callerLocation);
        sb.append(", callTime=").append(callTime);
        sb.append(", ringStartTime=").append(ringStartTime);
        sb.append(", ringEndTime=").append(ringEndTime);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", bridgeTime=").append(bridgeTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", talkTime=").append(talkTime);
        sb.append(", recordStartTime=").append(recordStartTime);
        sb.append(", recordTime=").append(recordTime);
        sb.append(", sipProtocol=").append(sipProtocol);
        sb.append(", record=").append(record);
        sb.append(", record2=").append(record2);
        sb.append(", record3=").append(record3);
        sb.append(", channelName=").append(channelName);
        sb.append(", hangupCause=").append(hangupCause);
        sb.append(", ringCause=").append(ringCause);
        sb.append(", sipStatus=").append(sipStatus);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
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
        CallDevice other = (CallDevice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCallId() == null ? other.getCallId() == null : this.getCallId().equals(other.getCallId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getAgentKey() == null ? other.getAgentKey() == null : this.getAgentKey().equals(other.getAgentKey()))
            && (this.getAgentName() == null ? other.getAgentName() == null : this.getAgentName().equals(other.getAgentName()))
            && (this.getDeviceType() == null ? other.getDeviceType() == null : this.getDeviceType().equals(other.getDeviceType()))
            && (this.getCdrType() == null ? other.getCdrType() == null : this.getCdrType().equals(other.getCdrType()))
            && (this.getCaller() == null ? other.getCaller() == null : this.getCaller().equals(other.getCaller()))
            && (this.getCalled() == null ? other.getCalled() == null : this.getCalled().equals(other.getCalled()))
            && (this.getDisplay() == null ? other.getDisplay() == null : this.getDisplay().equals(other.getDisplay()))
            && (this.getCalledLocation() == null ? other.getCalledLocation() == null : this.getCalledLocation().equals(other.getCalledLocation()))
            && (this.getCallerLocation() == null ? other.getCallerLocation() == null : this.getCallerLocation().equals(other.getCallerLocation()))
            && (this.getCallTime() == null ? other.getCallTime() == null : this.getCallTime().equals(other.getCallTime()))
            && (this.getRingStartTime() == null ? other.getRingStartTime() == null : this.getRingStartTime().equals(other.getRingStartTime()))
            && (this.getRingEndTime() == null ? other.getRingEndTime() == null : this.getRingEndTime().equals(other.getRingEndTime()))
            && (this.getAnswerTime() == null ? other.getAnswerTime() == null : this.getAnswerTime().equals(other.getAnswerTime()))
            && (this.getBridgeTime() == null ? other.getBridgeTime() == null : this.getBridgeTime().equals(other.getBridgeTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getTalkTime() == null ? other.getTalkTime() == null : this.getTalkTime().equals(other.getTalkTime()))
            && (this.getRecordStartTime() == null ? other.getRecordStartTime() == null : this.getRecordStartTime().equals(other.getRecordStartTime()))
            && (this.getRecordTime() == null ? other.getRecordTime() == null : this.getRecordTime().equals(other.getRecordTime()))
            && (this.getSipProtocol() == null ? other.getSipProtocol() == null : this.getSipProtocol().equals(other.getSipProtocol()))
            && (this.getRecord() == null ? other.getRecord() == null : this.getRecord().equals(other.getRecord()))
            && (this.getRecord2() == null ? other.getRecord2() == null : this.getRecord2().equals(other.getRecord2()))
            && (this.getRecord3() == null ? other.getRecord3() == null : this.getRecord3().equals(other.getRecord3()))
            && (this.getChannelName() == null ? other.getChannelName() == null : this.getChannelName().equals(other.getChannelName()))
            && (this.getHangupCause() == null ? other.getHangupCause() == null : this.getHangupCause().equals(other.getHangupCause()))
            && (this.getRingCause() == null ? other.getRingCause() == null : this.getRingCause().equals(other.getRingCause()))
            && (this.getSipStatus() == null ? other.getSipStatus() == null : this.getSipStatus().equals(other.getSipStatus()))
            && (this.getExt1() == null ? other.getExt1() == null : this.getExt1().equals(other.getExt1()))
            && (this.getExt2() == null ? other.getExt2() == null : this.getExt2().equals(other.getExt2()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getCallId() == null) ? 0 : getCallId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getAgentKey() == null) ? 0 : getAgentKey().hashCode());
        result = prime * result + ((getAgentName() == null) ? 0 : getAgentName().hashCode());
        result = prime * result + ((getDeviceType() == null) ? 0 : getDeviceType().hashCode());
        result = prime * result + ((getCdrType() == null) ? 0 : getCdrType().hashCode());
        result = prime * result + ((getCaller() == null) ? 0 : getCaller().hashCode());
        result = prime * result + ((getCalled() == null) ? 0 : getCalled().hashCode());
        result = prime * result + ((getDisplay() == null) ? 0 : getDisplay().hashCode());
        result = prime * result + ((getCalledLocation() == null) ? 0 : getCalledLocation().hashCode());
        result = prime * result + ((getCallerLocation() == null) ? 0 : getCallerLocation().hashCode());
        result = prime * result + ((getCallTime() == null) ? 0 : getCallTime().hashCode());
        result = prime * result + ((getRingStartTime() == null) ? 0 : getRingStartTime().hashCode());
        result = prime * result + ((getRingEndTime() == null) ? 0 : getRingEndTime().hashCode());
        result = prime * result + ((getAnswerTime() == null) ? 0 : getAnswerTime().hashCode());
        result = prime * result + ((getBridgeTime() == null) ? 0 : getBridgeTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getTalkTime() == null) ? 0 : getTalkTime().hashCode());
        result = prime * result + ((getRecordStartTime() == null) ? 0 : getRecordStartTime().hashCode());
        result = prime * result + ((getRecordTime() == null) ? 0 : getRecordTime().hashCode());
        result = prime * result + ((getSipProtocol() == null) ? 0 : getSipProtocol().hashCode());
        result = prime * result + ((getRecord() == null) ? 0 : getRecord().hashCode());
        result = prime * result + ((getRecord2() == null) ? 0 : getRecord2().hashCode());
        result = prime * result + ((getRecord3() == null) ? 0 : getRecord3().hashCode());
        result = prime * result + ((getChannelName() == null) ? 0 : getChannelName().hashCode());
        result = prime * result + ((getHangupCause() == null) ? 0 : getHangupCause().hashCode());
        result = prime * result + ((getRingCause() == null) ? 0 : getRingCause().hashCode());
        result = prime * result + ((getSipStatus() == null) ? 0 : getSipStatus().hashCode());
        result = prime * result + ((getExt1() == null) ? 0 : getExt1().hashCode());
        result = prime * result + ((getExt2() == null) ? 0 : getExt2().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}