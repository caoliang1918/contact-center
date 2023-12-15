package com.voice9.api.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Created by caoliang on 2021/11/29
 */
public class AgentImportExcel {

    /**
     * 坐席登录账号
     */
    @Excel(name = "坐席账号", isImportField = "true_st")
    private String agentKey;

    /**
     * 坐席名称
     */
    @Excel(name = "坐席名称", isImportField = "true_st")
    private String agentName;

    /**
     * 坐席sip号码
     */
    @Excel(name = "坐席sip号码")
    private Integer sip;

    /**
     * 坐席密码(明文)
     */
    @Excel(name = "坐席密码(明文)", isImportField = "true_st")
    private String passwd;

    /**
     * 绑定的电话号码
     */
    @Excel(name = "坐席手机号")
    private String sipPhone;

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

    public Integer getSip() {
        return sip;
    }

    public void setSip(Integer sip) {
        this.sip = sip;
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
}
