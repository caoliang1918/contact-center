package com.voice9.core.mapper;

import com.voice9.core.entity.AdminUser;
import com.voice9.core.mapper.base.BaseMapper;

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