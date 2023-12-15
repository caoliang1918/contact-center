package com.voice9.core.entity;

import java.io.Serializable;

/**
 * ivr流程表
 *
 * @author caoliang
 * @date   2022/03/15
 */
public class IvrWorkflow implements Serializable {
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
     * 流程名称
     */
    private String name;

    /**
     * 流程文件名
     */
    private String ossId;

    /**
     * 流程发布人
     */
    private String createUser;

    /**
     * 流程审核人
     */
    private String verifyUser;

    /**
     * 该流程用到的语音文件id，以英文逗号,分隔
     */
    private String voiceItem;

    /**
     * 用来存贮 ivr 流程启动所需要的参数描述
     */
    private String initParams;

    /**
     * 流程内容(ivr)
     */
    private String content;

    /**
     * 1转接，2咨询
     */
    private Integer type;

    /**
     * 流程状态    1：待发布   2：审核中  3：审核未通过  4：审核通过  5：已上线(ivr)
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

    public String getOssId() {
        return ossId;
    }

    public void setOssId(String ossId) {
        this.ossId = ossId == null ? null : ossId.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser == null ? null : verifyUser.trim();
    }

    public String getVoiceItem() {
        return voiceItem;
    }

    public void setVoiceItem(String voiceItem) {
        this.voiceItem = voiceItem == null ? null : voiceItem.trim();
    }

    public String getInitParams() {
        return initParams;
    }

    public void setInitParams(String initParams) {
        this.initParams = initParams;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        sb.append(", ossId=").append(ossId);
        sb.append(", createUser=").append(createUser);
        sb.append(", verifyUser=").append(verifyUser);
        sb.append(", voiceItem=").append(voiceItem);
        sb.append(", type=").append(type);
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
        IvrWorkflow other = (IvrWorkflow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getOssId() == null ? other.getOssId() == null : this.getOssId().equals(other.getOssId()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getVerifyUser() == null ? other.getVerifyUser() == null : this.getVerifyUser().equals(other.getVerifyUser()))
            && (this.getVoiceItem() == null ? other.getVoiceItem() == null : this.getVoiceItem().equals(other.getVoiceItem()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
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
        result = prime * result + ((getOssId() == null) ? 0 : getOssId().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getVerifyUser() == null) ? 0 : getVerifyUser().hashCode());
        result = prime * result + ((getVoiceItem() == null) ? 0 : getVoiceItem().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}