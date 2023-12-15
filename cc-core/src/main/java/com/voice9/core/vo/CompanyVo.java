package com.voice9.core.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by caoliang on 2021/4/27
 */
public class CompanyVo {

    @NotBlank(message = "企业名称不能为空")
    @Size(min = 2, max = 16, message = "企业名称必须在2,16字符")
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
    @NotNull(message = "企业缩写不能为空")
    @Size(min = 2, max = 8, message = "企业缩写必须在2,8字符")
    private String companyCode;

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
     * 1:呼出计费,2:呼入计费,3:双向计费
     */
    private Integer billType;

    /**
     * 0:预付费;1:后付费
     */
    private Integer payType;
    /**
     * 隐藏客户号码(0:不隐藏;1:隐藏)
     */
    @NotNull(message = "隐藏客户号码设置不能为空")
    @Range(min = 0, max = 1, message = "隐藏号码设置错误")
    private Integer hiddenCustomer;

    /**
     * IVR通道数
     */
    @NotNull(message = "IVR通道数不能为空")
    @Range(min = 1, max = 1000000, message = "IVR通道数设置错误")
    private Integer ivrLimit;

    /**
     * 开通坐席
     */
    @NotNull(message = "开通坐席数不能为空")
    @Range(min = 1, max = 1000000, message = "开通坐席数设置错误")
    private Integer agentLimit;

    /**
     * 开通技能组
     */
    @NotNull(message = "开通技能组数不能为空")
    @Range(min = 1, max = 2000, message = "开通技能组数设置错误")
    private Integer groupLimit;

    /**
     * 单技能组中坐席上限
     */
    @NotNull(message = "单技能组中坐席上限数不能为空")
    @Range(min = 0, max = 1000, message = "单技能组中坐席上限数设置错误")
    private Integer groupAgentLimit;

    /**
     * 话单回调通知
     */
    private String notifyUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath;
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
        this.companyCode = companyCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String toString() {
        return "CompanyVo{" +
                "name='" + name + '\'' +
                ", idPath='" + idPath + '\'' +
                ", pid=" + pid +
                ", companyCode='" + companyCode + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", balance=" + balance +
                ", billType=" + billType +
                ", payType=" + payType +
                ", hiddenCustomer=" + hiddenCustomer +
                ", ivrLimit=" + ivrLimit +
                ", agentLimit=" + agentLimit +
                ", groupLimit=" + groupLimit +
                ", groupAgentLimit=" + groupAgentLimit +
                ", notifyUrl='" + notifyUrl + '\'' +
                '}';
    }
}
