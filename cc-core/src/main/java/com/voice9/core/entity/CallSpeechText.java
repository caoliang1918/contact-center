package com.voice9.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author caoliang
 * @date 2022/06/13
 */
public class CallSpeechText implements Serializable {
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
     * 企业
     */
    private Long companyId;

    /**
     * call_id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long callId;

    /**
     * 设备标识
     */
    private String deviceId;

    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 唯一标识
     */
    private String speechId;

    /**
     * 内容
     */
    private String speechText;

    /**
     * asr厂商
     */
    private String asrProduct;

    /**
     * 意图
     */
    private String intention;

    /**
     * 质检结果
     */
    private Integer qualityStatus;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 月份
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

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getSpeechId() {
        return speechId;
    }

    public void setSpeechId(String speechId) {
        this.speechId = speechId == null ? null : speechId.trim();
    }

    public String getSpeechText() {
        return speechText;
    }

    public void setSpeechText(String speechText) {
        this.speechText = speechText == null ? null : speechText.trim();
    }

    public String getAsrProduct() {
        return asrProduct;
    }

    public void setAsrProduct(String asrProduct) {
        this.asrProduct = asrProduct == null ? null : asrProduct.trim();
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention == null ? null : intention.trim();
    }

    public Integer getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(Integer qualityStatus) {
        this.qualityStatus = qualityStatus;
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
        sb.append(", companyId=").append(companyId);
        sb.append(", callId=").append(callId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", speechId=").append(speechId);
        sb.append(", speechText=").append(speechText);
        sb.append(", asrProduct=").append(asrProduct);
        sb.append(", intention=").append(intention);
        sb.append(", qualityStatus=").append(qualityStatus);
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
        CallSpeechText other = (CallSpeechText) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
                && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
                && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
                && (this.getCallId() == null ? other.getCallId() == null : this.getCallId().equals(other.getCallId()))
                && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
                && (this.getSpeechId() == null ? other.getSpeechId() == null : this.getSpeechId().equals(other.getSpeechId()))
                && (this.getSpeechText() == null ? other.getSpeechText() == null : this.getSpeechText().equals(other.getSpeechText()))
                && (this.getAsrProduct() == null ? other.getAsrProduct() == null : this.getAsrProduct().equals(other.getAsrProduct()))
                && (this.getIntention() == null ? other.getIntention() == null : this.getIntention().equals(other.getIntention()))
                && (this.getQualityStatus() == null ? other.getQualityStatus() == null : this.getQualityStatus().equals(other.getQualityStatus()))
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
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getSpeechId() == null) ? 0 : getSpeechId().hashCode());
        result = prime * result + ((getSpeechText() == null) ? 0 : getSpeechText().hashCode());
        result = prime * result + ((getAsrProduct() == null) ? 0 : getAsrProduct().hashCode());
        result = prime * result + ((getIntention() == null) ? 0 : getIntention().hashCode());
        result = prime * result + ((getQualityStatus() == null) ? 0 : getQualityStatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}