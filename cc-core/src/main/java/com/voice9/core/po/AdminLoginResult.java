package com.voice9.core.po;

import java.util.List;

/**
 * Created by caoliang on 2022/4/4
 */
public class AdminLoginResult {
    /**
     * 登录时间
     */
    private Long cts;

    /**
     * token
     */
    private String token;


    /**
     * 账号信息
     */
    private UserInfo userInfo;

    /**
     * 菜单列表
     */
    private List<MenusPo> menus;


    public Long getCts() {
        return cts;
    }

    public void setCts(Long cts) {
        this.cts = cts;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<MenusPo> getMenus() {
        return menus;
    }

    public void setMenus(List<MenusPo> menus) {
        this.menus = menus;
    }
}
