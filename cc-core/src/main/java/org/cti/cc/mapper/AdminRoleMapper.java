package org.cti.cc.mapper;

import org.cti.cc.entity.AdminRole;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
}