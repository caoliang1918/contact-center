package org.cti.cc.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by caoliang on 2022/1/18
 */
public class AdminLogin {

    @NotNull(message = "username不能为空")
    private String username;

    @NotNull(message = "passwd不能为空")
    private String passwd;

    /**
     * 客户端ip
     */
    private String clientIp;

    /**
     * 验证码
     */
    private String captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
