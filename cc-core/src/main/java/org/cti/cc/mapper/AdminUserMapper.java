package org.cti.cc.mapper;

import org.cti.cc.entity.AdminUser;
import org.cti.cc.mapper.base.BaseMapper;

public interface AdminUserMapper extends BaseMapper<AdminUser> {

    /**
     * 查询坐席
     *
     * @param username
     * @return
     */
    AdminUser adminLogin(String username);
}