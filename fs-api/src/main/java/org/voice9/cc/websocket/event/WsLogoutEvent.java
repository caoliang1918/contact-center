package org.voice9.cc.websocket.event;

import org.voice9.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/10/26
 * <p>
 * 坐席退出
 */
public class WsLogoutEvent extends WsBaseEvent {

    /**
     *
     */
    private String agentKey;

    /**
     *
     */
    private String token;

    /**
     * 工作方式
     */
    private String workType;

    /**
     * 登录方式
     */
    private String loginType;

    @Override
    public String getAgentKey() {
        return agentKey;
    }

    @Override
    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
