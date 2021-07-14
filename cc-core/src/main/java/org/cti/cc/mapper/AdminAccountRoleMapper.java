package org.cti.cc.mapper;

import org.cti.cc.entity.AdminAccountRole;

public interface AdminAccountRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminAccountRole record);

    int insertSelective(AdminAccountRole record);

    AdminAccountRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminAccountRole record);

    int updateByPrimaryKey(AdminAccountRole record);
}