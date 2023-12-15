package com.voice9.core.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by caoliang on 2021/4/21
 */
public class AgentVo {

    private Long id;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 坐席账户
     */
    @NotBlank(message = "坐席不能为空")
    @Size(min = 4, max = 16, message = "坐席工号必须在4-16个字符")
    private String agentId;

    /**
     * 坐席名称
     */
    private String agentName;

    /**
     * rest 接口回调地址
     */
    @Size(min = 10, max = 100, message = "webHook地址必须在10-100字符")
    private String webHook;

    /**
     * 坐席分机号
     */
    private String agentCode;

    /**
     * 1：坐席sip号
     * 2：webrtc
     * 3：坐席手机号
     */
    @NotNull(message = "登录方式不能为空")
    @Range(min = 1, max = 3, message = "登录方式错误")
    private Integer loginType;


    /**
     * 1:普通方式 2:预测方式
     */
    @Range(min = 1, max = 2, message = "坐席工作方式错误")
    private Integer workType;

    /**
     * 1:普通坐席,2:班长
     */
    @Range(min = 1, max = 2, message = "坐席类型错误")
    private Integer agentType;

    /**
     * 座席密码,前端传过来已经是sha256加密过的。
     */
    @NotBlank(message = "坐席密码不能为空")
    @Size(min = 64, message = "密码长度不对")
    private String passwd;

    /**
     * 绑定的电话号码
     */
    private String sipPhone;

    /**
     * 话后自动空闲间隔时长
     */
    private Integer afterInterval;


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

    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSipPhone() {
        return sipPhone;
    }

    public void setSipPhone(String sipPhone) {
        this.sipPhone = sipPhone;
    }

    public Integer getAfterInterval() {
        return afterInterval;
    }

    public void setAfterInterval(Integer afterInterval) {
        this.afterInterval = afterInterval;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    @Override
    public String toString() {
        return "AgentVo{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", agentId='" + agentId + '\'' +
                ", agentName='" + agentName + '\'' +
                ", webHook='" + webHook + '\'' +
                ", agentCode='" + agentCode + '\'' +
                ", loginType=" + loginType +
                ", workType=" + workType +
                ", agentType=" + agentType +
                ", passwd='" + passwd + '\'' +
                ", sipPhone='" + sipPhone + '\'' +
                ", afterInterval=" + afterInterval +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                '}';
    }
}
