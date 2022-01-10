package org.cti.cc.mapper;

import org.cti.cc.entity.AdminMenu;

import java.util.List;

public interface AdminMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminMenu record);

    int insertSelective(AdminMenu record);

    AdminMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminMenu record);

    int updateByPrimaryKey(AdminMenu record);


    /**
     * 查询所有菜单
     *
     * @return
     */
    List<AdminMenu> selectAll();

    /**
     *
     * @param uid
     * @return
     */
    List<AdminMenu> selectList(Long uid);
}