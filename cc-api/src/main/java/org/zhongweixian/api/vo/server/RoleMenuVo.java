package org.zhongweixian.api.vo.server;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by caoliang on 2022/4/11
 */
public class RoleMenuVo {

    @NotNull
    private Long roleId;

    private List<Long> menuIds;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
