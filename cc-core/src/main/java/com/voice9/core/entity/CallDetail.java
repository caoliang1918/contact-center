package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 通话流程表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class CallDetail implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     * 创建时间
     */
    private Long cts;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 通话ID
     */
    private Long callId;

    /**
     * 顺序
     */
    private Integer detailIndex;

    /**
     * 类型(1:进vdn,2:进ivr,3:技能组,4:按键收号,5:外线)
     */
    private Integer transferType;

    /**
     * 转接ID
     */
    private Long transferId;

    /**
     * 出队列原因:排队挂机或者转坐席
     */
    private String reason;

    private String month;

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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Integer getDetailIndex() {
        return detailIndex;
    }

    public void setDetailIndex(Integer detailIndex) {
        this.detailIndex = detailIndex;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", callId=").append(callId);
        sb.append(", detailIndex=").append(detailIndex);
        sb.append(", transferType=").append(transferType);
        sb.append(", transferId=").append(transferId);
        sb.append(", reason=").append(reason);
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
        CallDetail other = (CallDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getCallId() == null ? other.getCallId() == null : this.getCallId().equals(other.getCallId()))
            && (this.getDetailIndex() == null ? other.getDetailIndex() == null : this.getDetailIndex().equals(other.getDetailIndex()))
            && (this.getTransferType() == null ? other.getTransferType() == null : this.getTransferType().equals(other.getTransferType()))
            && (this.getTransferId() == null ? other.getTransferId() == null : this.getTransferId().equals(other.getTransferId()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
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
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getCallId() == null) ? 0 : getCallId().hashCode());
        result = prime * result + ((getDetailIndex() == null) ? 0 : getDetailIndex().hashCode());
        result = prime * result + ((getTransferType() == null) ? 0 : getTransferType().hashCode());
        result = prime * result + ((getTransferId() == null) ? 0 : getTransferId().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getExt1() == null) ? 0 : getExt1().hashCode());
        result = prime * result + ((getExt2() == null) ? 0 : getExt2().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}