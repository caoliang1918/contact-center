package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 站点类型配置信息
 *
 * @author caoliang
 * @date 2021/09/01
 */
public class Station implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     *
     */
    private Long cts;

    /**
     *
     */
    private Long uts;

    /**
     *
     */
    private Integer applicationId;

    /**
     *
     */
    private Integer applicationType;

    /**
     *
     */
    private String applicationGroup;

    /**
     *
     */
    private String applicationHost;

    /**
     *
     */
    private Integer applicationPort;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String pwd;

    /**
     *
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

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(Integer applicationType) {
        this.applicationType = applicationType;
    }

    public String getApplicationGroup() {
        return applicationGroup;
    }

    public void setApplicationGroup(String applicationGroup) {
        this.applicationGroup = applicationGroup == null ? null : applicationGroup.trim();
    }

    public String getApplicationHost() {
        return applicationHost;
    }

    public void setApplicationHost(String applicationHost) {
        this.applicationHost = applicationHost == null ? null : applicationHost.trim();
    }

    public Integer getApplicationPort() {
        return applicationPort;
    }

    public void setApplicationPort(Integer applicationPort) {
        this.applicationPort = applicationPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHost() {
        return this.getApplicationHost() + ":" + this.getApplicationPort();
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
        sb.append(", applicationId=").append(applicationId);
        sb.append(", applicationType=").append(applicationType);
        sb.append(", applicationGroup=").append(applicationGroup);
        sb.append(", applicationHost=").append(applicationHost);
        sb.append(", applicationPort=").append(applicationPort);
        sb.append(", username=").append(username);
        sb.append(", pwd=").append(pwd);
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
        Station other = (Station) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
                && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
                && (this.getApplicationId() == null ? other.getApplicationId() == null : this.getApplicationId().equals(other.getApplicationId()))
                && (this.getApplicationType() == null ? other.getApplicationType() == null : this.getApplicationType().equals(other.getApplicationType()))
                && (this.getApplicationGroup() == null ? other.getApplicationGroup() == null : this.getApplicationGroup().equals(other.getApplicationGroup()))
                && (this.getApplicationHost() == null ? other.getApplicationHost() == null : this.getApplicationHost().equals(other.getApplicationHost()))
                && (this.getApplicationPort() == null ? other.getApplicationPort() == null : this.getApplicationPort().equals(other.getApplicationPort()))
                && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
                && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getApplicationId() == null) ? 0 : getApplicationId().hashCode());
        result = prime * result + ((getApplicationType() == null) ? 0 : getApplicationType().hashCode());
        result = prime * result + ((getApplicationGroup() == null) ? 0 : getApplicationGroup().hashCode());
        result = prime * result + ((getApplicationHost() == null) ? 0 : getApplicationHost().hashCode());
        result = prime * result + ((getApplicationPort() == null) ? 0 : getApplicationPort().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}