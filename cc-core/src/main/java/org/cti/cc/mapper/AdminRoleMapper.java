package org.cti.cc.mapper;

import org.cti.cc.entity.AdminRole;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.RolePo;

import java.util.List;
import java.util.Map;

public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 角色列表
     * @param params
     * @return
     */
    List<RolePo> selectRoleMenuList(Map<String, Object> params);

    List<AdminRole> selectList(Map<String, Object> params);


}