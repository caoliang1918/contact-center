package org.cti.cc.mapper;

import org.cti.cc.entity.AdminPermissionRole;

public interface AdminPermissionRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminPermissionRole record);

    int insertSelective(AdminPermissionRole record);

    AdminPermissionRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminPermissionRole record);

    int updateByPrimaryKey(AdminPermissionRole record);
}