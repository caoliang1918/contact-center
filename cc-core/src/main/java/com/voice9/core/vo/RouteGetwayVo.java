package com.voice9.core.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RouteGetwayVo {

    private Long id;


    @NotBlank(message = "网关名称不能为空")
    @Size(min = 2, max = 16, message = "网关名称必须在2,16字符")
    private String name;

    /**
     * 媒体地址
     */
    @NotBlank(message = "媒体地址不能为空")
    private String mediaHost;

    /**
     * 媒体端口
     */
    @NotNull(message = "媒体端口不能为空")
    @Range(min = 10000, max = 65535, message = "媒体端口错误")
    private Integer mediaPort;

    /**
     * 主叫前缀
     */
    @Size(max = 8, message = "主叫前缀不能超过8位")
    private String callerPrefix;

    /**
     * 被叫前缀
     */
    @Size(max = 8, message = "被叫前缀不能超过8位")
    private String calledPrefix;

    /**
     * 媒体拨号计划文件
     */
    @NotNull(message = "profile规则不能为空")
    @Size(min = 4, max = 32, message = "profile长度必须在4-32位")
    private String profile;

    /**
     * sip协议头
     */
    @Size(max = 36, message = "sip协议头不能超过36位")
    private String sipHeader1;

    /**
     * sip协议头
     */
    @Size(max = 36, message = "sip协议头不能超过36位")
    private String sipHeader2;

    /**
     * sip协议头
     */
    @Size(max = 36, message = "sip协议头不能超过36位")
    private String sipHeader3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMediaHost() {
        return mediaHost;
    }

    public void setMediaHost(String mediaHost) {
        this.mediaHost = mediaHost;
    }

    public Integer getMediaPort() {
        return mediaPort;
    }

    public void setMediaPort(Integer mediaPort) {
        this.mediaPort = mediaPort;
    }

    public String getCallerPrefix() {
        return callerPrefix;
    }

    public void setCallerPrefix(String callerPrefix) {
        this.callerPrefix = callerPrefix;
    }

    public String getCalledPrefix() {
        return calledPrefix;
    }

    public void setCalledPrefix(String calledPrefix) {
        this.calledPrefix = calledPrefix;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSipHeader1() {
        return sipHeader1;
    }

    public void setSipHeader1(String sipHeader1) {
        this.sipHeader1 = sipHeader1;
    }

    public String getSipHeader2() {
        return sipHeader2;
    }

    public void setSipHeader2(String sipHeader2) {
        this.sipHeader2 = sipHeader2;
    }

    public String getSipHeader3() {
        return sipHeader3;
    }

    public void setSipHeader3(String sipHeader3) {
        this.sipHeader3 = sipHeader3;
    }
}
