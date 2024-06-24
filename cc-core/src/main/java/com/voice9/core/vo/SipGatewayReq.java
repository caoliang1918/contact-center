package com.voice9.core.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by caoliang on 2024/6/24
 */
public class SipGatewayReq {

    /**
     * PK
     */
    private Long id;

    /**
     * 企业id
     */
    @NotNull(message = "companyId不能为空")
    private Long companyId;

    /**
     * 账号
     */
    @NotBlank(message = "sip账号不能为空")
    @Length(min = 2, max = 16, message = "sip账号长度在2-16字符")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "sip密码不能为空")
    @Length(min = 2, max = 16, message = "sip密码长度在2-16字符")
    private String passwd;


    /**
     * 注册地址
     */
    @NotBlank(message = "注册地址不能为空")
    private String registerAddr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "companyId不能为空") Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(@NotNull(message = "companyId不能为空") Long companyId) {
        this.companyId = companyId;
    }

    public @NotBlank(message = "sip账号不能为空") @Length(min = 2, max = 16, message = "sip账号长度在2-16字符") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "sip账号不能为空") @Length(min = 2, max = 16, message = "sip账号长度在2-16字符") String username) {
        this.username = username;
    }

    public @NotBlank(message = "sip密码不能为空") @Length(min = 2, max = 16, message = "sip密码长度在2-16字符") String getPasswd() {
        return passwd;
    }

    public void setPasswd(@NotBlank(message = "sip密码不能为空") @Length(min = 2, max = 16, message = "sip密码长度在2-16字符") String passwd) {
        this.passwd = passwd;
    }

    public String getRegisterAddr() {
        return registerAddr;
    }

    public void setRegisterAddr(String registerAddr) {
        this.registerAddr = registerAddr;
    }

    @Override
    public String toString() {
        return "SipGatewayBo{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", registerAddr='" + registerAddr + '\'' +
                '}';
    }
}
