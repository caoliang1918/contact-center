package com.voice9.core.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by caoliang on 2021/5/26
 */
public class AgentSipVo {

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
    private Long companyId;

    /**
     *
     */
    @NotBlank(message = "sip号码不能为空")
    @Size(min = 5, max = 16, message = "sip号码必须是5,16字符")
    private String sip;

    /**
     *
     */
    private Long agentId;

    /**
     *
     */
    @NotNull(message = "sip密码不能为空")
    @Size(min = 8, max = 16, message = "sip密码必须是8,16字符")
    private String sipPwd;

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

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getSipPwd() {
        return sipPwd;
    }

    public void setSipPwd(String sipPwd) {
        this.sipPwd = sipPwd;
    }
}
