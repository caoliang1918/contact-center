package org.cti.cc.mapper;

import org.cti.cc.entity.AdminUser;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface AdminUserMapper extends BaseMapper<AdminUser> {

    /**
     * 查询坐席
     *
     * @param username
     * @return
     */
    AdminUser adminLogin(String username);


    /**
     * 根据角色查询账号
     *
     * @param roleId
     * @return
     */
    List<AdminUser> selectByRoleId(Long roleId);
}