package org.cti.cc.mapper;

import org.cti.cc.entity.AdminPermission;

public interface AdminPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminPermission record);

    int insertSelective(AdminPermission record);

    AdminPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminPermission record);

    int updateByPrimaryKey(AdminPermission record);
}