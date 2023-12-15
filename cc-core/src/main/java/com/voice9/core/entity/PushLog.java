package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 话单推送记录表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class PushLog implements Serializable {
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
     * 企业id
     */
    private Long companyId;

    /**
     * callid
     */
    private Long callId;

    /**
     * 推送地址
     */
    private String cdrNotifyUrl;

    /**
     * 推送内容
     */
    private String content;

    /**
     * 推送次数
     */
    private Integer pushTimes;

    /**
     * 推送返回值
     */
    private String pushResponse;

    /**
     * 状态 1:推送失败；2:推送成功
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

    public String getCdrNotifyUrl() {
        return cdrNotifyUrl;
    }

    public void setCdrNotifyUrl(String cdrNotifyUrl) {
        this.cdrNotifyUrl = cdrNotifyUrl == null ? null : cdrNotifyUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPushTimes() {
        return pushTimes;
    }

    public void setPushTimes(Integer pushTimes) {
        this.pushTimes = pushTimes;
    }

    public String getPushResponse() {
        return pushResponse;
    }

    public void setPushResponse(String pushResponse) {
        this.pushResponse = pushResponse == null ? null : pushResponse.trim();
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
        sb.append(", cdrNotifyUrl=").append(cdrNotifyUrl);
        sb.append(", content=").append(content);
        sb.append(", pushTimes=").append(pushTimes);
        sb.append(", pushResponse=").append(pushResponse);
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
        PushLog other = (PushLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCallId() == null ? other.getCallId() == null : this.getCallId().equals(other.getCallId()))
            && (this.getCdrNotifyUrl() == null ? other.getCdrNotifyUrl() == null : this.getCdrNotifyUrl().equals(other.getCdrNotifyUrl()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getPushTimes() == null ? other.getPushTimes() == null : this.getPushTimes().equals(other.getPushTimes()))
            && (this.getPushResponse() == null ? other.getPushResponse() == null : this.getPushResponse().equals(other.getPushResponse()))
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
        result = prime * result + ((getCdrNotifyUrl() == null) ? 0 : getCdrNotifyUrl().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getPushTimes() == null) ? 0 : getPushTimes().hashCode());
        result = prime * result + ((getPushResponse() == null) ? 0 : getPushResponse().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}