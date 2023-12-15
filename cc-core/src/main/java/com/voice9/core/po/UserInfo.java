package com.voice9.core.po;

/**
 * Created by caoliang on 2022/4/4
 */
public class UserInfo {

    /**
     * 时区
     */
    private Integer gmt;

    /**
     * 语言
     */
    private String lan;

    /**
     * 账号
     */
    private String username;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业缩写
     */
    private String companyCode;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 文件存储服务地址
     */
    private String ossServer;

    /**
     * 客户端ip
     */
    private String clientIp;


    public Integer getGmt() {
        return gmt;
    }

    public void setGmt(Integer gmt) {
        this.gmt = gmt;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOssServer() {
        return ossServer;
    }

    public void setOssServer(String ossServer) {
        this.ossServer = ossServer;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
