package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 企业信息表
 *
 * @author caoliang
 * @date 2020/06/06
 */
public class Company implements Serializable {
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
     * 名称
     */
    private String name;

    /**
     * 父企业ID
     */
    private String idPath;

    /**
     * 父企业
     */
    private Long pid;

    /**
     * 简称
     */
    private String companyCode;

    /**
     * 时区概念(默认是GTM+8)
     */
    private Integer gmt;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 电话
     */
    private String phone;

    /**
     * 金额
     */
    private Long balance;

    /**
     * 1:呼出计费,2:呼入计费,3:双向计费,0:全免费
     */
    private Integer billType;

    /**
     * 0:预付费;1:后付费
     */
    private Integer payType;

    /**
     * 隐藏客户号码(0:不隐藏;1:隐藏)
     */
    private Integer hiddenCustomer;

    /**
     * 坐席密码等级(1:不限制 2:数字和字母 3:大小写字母和数字组合)
     */
    private Integer secretType;

    /**
     * 验证秘钥
     */
    private String secretKey;

    /**
     * IVR通道数
     */
    private Integer ivrLimit;

    /**
     * 开通坐席
     */
    private Integer agentLimit;

    /**
     * 开通技能组
     */
    private Integer groupLimit;

    /**
     * 单技能组中坐席上限
     */
    private Integer groupAgentLimit;

    /**
     * 录音保留x个月
     */
    private Integer recordStorage;

    /**
     * 话单回调通知
     */
    private String notifyUrl;

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
     * 状态(0:禁用企业,1:免费企业;2:试用企业,3:付费企业)
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

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath == null ? null : idPath.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public Integer getGmt() {
        return gmt;
    }

    public void setGmt(Integer gmt) {
        this.gmt = gmt;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getHiddenCustomer() {
        return hiddenCustomer;
    }

    public void setHiddenCustomer(Integer hiddenCustomer) {
        this.hiddenCustomer = hiddenCustomer;
    }

    public Integer getSecretType() {
        return secretType;
    }

    public void setSecretType(Integer secretType) {
        this.secretType = secretType;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey == null ? null : secretKey.trim();
    }

    public Integer getIvrLimit() {
        return ivrLimit;
    }

    public void setIvrLimit(Integer ivrLimit) {
        this.ivrLimit = ivrLimit;
    }

    public Integer getAgentLimit() {
        return agentLimit;
    }

    public void setAgentLimit(Integer agentLimit) {
        this.agentLimit = agentLimit;
    }

    public Integer getGroupLimit() {
        return groupLimit;
    }

    public void setGroupLimit(Integer groupLimit) {
        this.groupLimit = groupLimit;
    }

    public Integer getGroupAgentLimit() {
        return groupAgentLimit;
    }

    public void setGroupAgentLimit(Integer groupAgentLimit) {
        this.groupAgentLimit = groupAgentLimit;
    }

    public Integer getRecordStorage() {
        return recordStorage;
    }

    public void setRecordStorage(Integer recordStorage) {
        this.recordStorage = recordStorage;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
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
        sb.append(", name=").append(name);
        sb.append(", idPath=").append(idPath);
        sb.append(", pid=").append(pid);
        sb.append(", companyCode=").append(companyCode);
        sb.append(", contact=").append(contact);
        sb.append(", phone=").append(phone);
        sb.append(", balance=").append(balance);
        sb.append(", billType=").append(billType);
        sb.append(", payType=").append(payType);
        sb.append(", hiddenCustomer=").append(hiddenCustomer);
        sb.append(", secretKey=").append(secretKey);
        sb.append(", ivrLimit=").append(ivrLimit);
        sb.append(", agentLimit=").append(agentLimit);
        sb.append(", groupLimit=").append(groupLimit);
        sb.append(", groupAgentLimit=").append(groupAgentLimit);
        sb.append(", recordStorage=").append(recordStorage);
        sb.append(", notifyUrl=").append(notifyUrl);
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
        Company other = (Company) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
                && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getIdPath() == null ? other.getIdPath() == null : this.getIdPath().equals(other.getIdPath()))
                && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
                && (this.getCompanyCode() == null ? other.getCompanyCode() == null : this.getCompanyCode().equals(other.getCompanyCode()))
                && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
                && (this.getBillType() == null ? other.getBillType() == null : this.getBillType().equals(other.getBillType()))
                && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
                && (this.getHiddenCustomer() == null ? other.getHiddenCustomer() == null : this.getHiddenCustomer().equals(other.getHiddenCustomer()))
                && (this.getSecretKey() == null ? other.getSecretKey() == null : this.getSecretKey().equals(other.getSecretKey()))
                && (this.getIvrLimit() == null ? other.getIvrLimit() == null : this.getIvrLimit().equals(other.getIvrLimit()))
                && (this.getAgentLimit() == null ? other.getAgentLimit() == null : this.getAgentLimit().equals(other.getAgentLimit()))
                && (this.getGroupLimit() == null ? other.getGroupLimit() == null : this.getGroupLimit().equals(other.getGroupLimit()))
                && (this.getGroupAgentLimit() == null ? other.getGroupAgentLimit() == null : this.getGroupAgentLimit().equals(other.getGroupAgentLimit()))
                && (this.getRecordStorage() == null ? other.getRecordStorage() == null : this.getRecordStorage().equals(other.getRecordStorage()))
                && (this.getNotifyUrl() == null ? other.getNotifyUrl() == null : this.getNotifyUrl().equals(other.getNotifyUrl()))
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
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIdPath() == null) ? 0 : getIdPath().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCompanyCode() == null) ? 0 : getCompanyCode().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getBillType() == null) ? 0 : getBillType().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getHiddenCustomer() == null) ? 0 : getHiddenCustomer().hashCode());
        result = prime * result + ((getSecretKey() == null) ? 0 : getSecretKey().hashCode());
        result = prime * result + ((getIvrLimit() == null) ? 0 : getIvrLimit().hashCode());
        result = prime * result + ((getAgentLimit() == null) ? 0 : getAgentLimit().hashCode());
        result = prime * result + ((getGroupLimit() == null) ? 0 : getGroupLimit().hashCode());
        result = prime * result + ((getGroupAgentLimit() == null) ? 0 : getGroupAgentLimit().hashCode());
        result = prime * result + ((getRecordStorage() == null) ? 0 : getRecordStorage().hashCode());
        result = prime * result + ((getNotifyUrl() == null) ? 0 : getNotifyUrl().hashCode());
        result = prime * result + ((getExt1() == null) ? 0 : getExt1().hashCode());
        result = prime * result + ((getExt2() == null) ? 0 : getExt2().hashCode());
        result = prime * result + ((getExt3() == null) ? 0 : getExt3().hashCode());
        result = prime * result + ((getExt4() == null) ? 0 : getExt4().hashCode());
        result = prime * result + ((getExt5() == null) ? 0 : getExt5().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}