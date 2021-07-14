package org.cti.cc.po;

import org.cti.cc.entity.AdminAccount;

public class AdminAccountInfo extends AdminAccount {

    //角色

    //权限列表

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     *  绑定企业id
     */
    private Long bindCompanyId;

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getBindCompanyId() {
        return bindCompanyId;
    }

    public void setBindCompanyId(Long bindCompanyId) {
        this.bindCompanyId = bindCompanyId;
    }
}
