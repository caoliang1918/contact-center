package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 呼叫按键表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class CallDtmf implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     * 创建时间
     */
    private Long cts;

    /**
     * 按键号码
     */
    private String dtmfKey;

    /**
     * 业务流程id
     */
    private Long processId;

    /**
     * 通话标识id
     */
    private Long callId;

    /**
     * 按键时间
     */
    private Long dtmfTime;

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

    public String getDtmfKey() {
        return dtmfKey;
    }

    public void setDtmfKey(String dtmfKey) {
        this.dtmfKey = dtmfKey == null ? null : dtmfKey.trim();
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Long getDtmfTime() {
        return dtmfTime;
    }

    public void setDtmfTime(Long dtmfTime) {
        this.dtmfTime = dtmfTime;
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
        sb.append(", dtmfKey=").append(dtmfKey);
        sb.append(", processId=").append(processId);
        sb.append(", callId=").append(callId);
        sb.append(", dtmfTime=").append(dtmfTime);
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
        CallDtmf other = (CallDtmf) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getDtmfKey() == null ? other.getDtmfKey() == null : this.getDtmfKey().equals(other.getDtmfKey()))
            && (this.getProcessId() == null ? other.getProcessId() == null : this.getProcessId().equals(other.getProcessId()))
            && (this.getCallId() == null ? other.getCallId() == null : this.getCallId().equals(other.getCallId()))
            && (this.getDtmfTime() == null ? other.getDtmfTime() == null : this.getDtmfTime().equals(other.getDtmfTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getDtmfKey() == null) ? 0 : getDtmfKey().hashCode());
        result = prime * result + ((getProcessId() == null) ? 0 : getProcessId().hashCode());
        result = prime * result + ((getCallId() == null) ? 0 : getCallId().hashCode());
        result = prime * result + ((getDtmfTime() == null) ? 0 : getDtmfTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}