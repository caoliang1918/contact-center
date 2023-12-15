package com.voice9.core.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by caoliang on 2022/5/15
 */
public class AgentLoginVo {


    /**
     * 坐席账户
     */
    @NotBlank(message = "坐席不能为空")
    @Size(min = 4, max = 16, message = "坐席工号必须在4-16个字符")
    private String agentKey;

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
    private Integer loginType;

    /**
     *
     */
    private Integer workType;

    /**
     * 座席密码,前端传过来已经是sha256加密过的。
     */
    @NotNull(message = "坐席密码不能为空")
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
     * 强制登录
     */
    private boolean forceLogin;

    /**
     * 主叫显号
     */
    private String display;

    /**
     * 振铃时长
     */
    private Integer ringTime;


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

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
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

    public boolean isForceLogin() {
        return forceLogin;
    }

    public void setForceLogin(boolean forceLogin) {
        this.forceLogin = forceLogin;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Integer getRingTime() {
        return ringTime;
    }

    public void setRingTime(Integer ringTime) {
        this.ringTime = ringTime;
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
}
