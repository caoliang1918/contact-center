package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 媒体网关表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class RouteGetway implements Serializable {
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
     * 号码
     */
    private String name;

    /**
     * 媒体地址
     */
    private String mediaHost;

    /**
     * 媒体端口
     */
    private Integer mediaPort;

    /**
     * 主叫号码前缀
     */
    private String callerPrefix;

    /**
     * 被叫号码前缀
     */
    private String calledPrefix;

    /**
     * 媒体拨号计划文件
     */
    private String profile;

    /**
     * sip头1
     */
    private String sipHeader1;

    /**
     * sip头2
     */
    private String sipHeader2;

    /**
     * sip头3
     */
    private String sipHeader3;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMediaHost() {
        return mediaHost;
    }

    public void setMediaHost(String mediaHost) {
        this.mediaHost = mediaHost == null ? null : mediaHost.trim();
    }

    public Integer getMediaPort() {
        return mediaPort;
    }

    public void setMediaPort(Integer mediaPort) {
        this.mediaPort = mediaPort;
    }

    public String getCallerPrefix() {
        return callerPrefix;
    }

    public void setCallerPrefix(String callerPrefix) {
        this.callerPrefix = callerPrefix == null ? null : callerPrefix.trim();
    }

    public String getCalledPrefix() {
        return calledPrefix;
    }

    public void setCalledPrefix(String calledPrefix) {
        this.calledPrefix = calledPrefix == null ? null : calledPrefix.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSipHeader1() {
        return sipHeader1;
    }

    public void setSipHeader1(String sipHeader1) {
        this.sipHeader1 = sipHeader1 == null ? null : sipHeader1.trim();
    }

    public String getSipHeader2() {
        return sipHeader2;
    }

    public void setSipHeader2(String sipHeader2) {
        this.sipHeader2 = sipHeader2 == null ? null : sipHeader2.trim();
    }

    public String getSipHeader3() {
        return sipHeader3;
    }

    public void setSipHeader3(String sipHeader3) {
        this.sipHeader3 = sipHeader3 == null ? null : sipHeader3.trim();
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
        sb.append(", name=").append(name);
        sb.append(", mediaHost=").append(mediaHost);
        sb.append(", mediaPort=").append(mediaPort);
        sb.append(", callerPrefix=").append(callerPrefix);
        sb.append(", calledPrefix=").append(calledPrefix);
        sb.append(", sipHeader1=").append(sipHeader1);
        sb.append(", sipHeader2=").append(sipHeader2);
        sb.append(", sipHeader3=").append(sipHeader3);
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
        RouteGetway other = (RouteGetway) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMediaHost() == null ? other.getMediaHost() == null : this.getMediaHost().equals(other.getMediaHost()))
            && (this.getMediaPort() == null ? other.getMediaPort() == null : this.getMediaPort().equals(other.getMediaPort()))
            && (this.getCallerPrefix() == null ? other.getCallerPrefix() == null : this.getCallerPrefix().equals(other.getCallerPrefix()))
            && (this.getCalledPrefix() == null ? other.getCalledPrefix() == null : this.getCalledPrefix().equals(other.getCalledPrefix()))
            && (this.getSipHeader1() == null ? other.getSipHeader1() == null : this.getSipHeader1().equals(other.getSipHeader1()))
            && (this.getSipHeader2() == null ? other.getSipHeader2() == null : this.getSipHeader2().equals(other.getSipHeader2()))
            && (this.getSipHeader3() == null ? other.getSipHeader3() == null : this.getSipHeader3().equals(other.getSipHeader3()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMediaHost() == null) ? 0 : getMediaHost().hashCode());
        result = prime * result + ((getMediaPort() == null) ? 0 : getMediaPort().hashCode());
        result = prime * result + ((getCallerPrefix() == null) ? 0 : getCallerPrefix().hashCode());
        result = prime * result + ((getCalledPrefix() == null) ? 0 : getCalledPrefix().hashCode());
        result = prime * result + ((getSipHeader1() == null) ? 0 : getSipHeader1().hashCode());
        result = prime * result + ((getSipHeader2() == null) ? 0 : getSipHeader2().hashCode());
        result = prime * result + ((getSipHeader3() == null) ? 0 : getSipHeader3().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}