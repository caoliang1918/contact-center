package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/10/26
 * <p>
 * 坐席登录
 */
public class WsLoginEvnet extends WsBaseEvent {

    /**
     * token 模式
     */
    private String token;

    /**
     * 密码模式
     */
    private String passwd;

    /**
     * 1：坐席sip号
     * 2：webrtc
     * 3：坐席手机号
     */
    private Integer loginType;

    /**
     * 预测、手动
     */
    private Integer workType;

    /**
     * 坐席端或者sdk传过来的sip或者手机号
     */
    private String sip;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }


    @Override
    public String toString() {
        return "WsLoginEvnet{" +
                "token='" + token + '\'' +
                ", passwd='" + passwd + '\'' +
                ", loginType='" + loginType + '\'' +
                ", workType='" + workType + '\'' +
                ", sip='" + sip + '\'' +
                '}';
    }
}
