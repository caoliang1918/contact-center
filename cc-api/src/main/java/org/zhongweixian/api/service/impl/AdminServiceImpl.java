package org.zhongweixian.api.service.impl;

import org.cti.cc.entity.AdminMenu;
import org.cti.cc.mapper.AdminMenuMapper;
import org.cti.cc.po.MenusPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhongweixian.api.service.AdminService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2022/1/7
 */
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Override
    public List<MenusPo> getMenus(Long uid) {
        List<MenusPo> menusPoList = new ArrayList<>();
        List<AdminMenu> adminMenus = null;
        if (uid == 1) {
            adminMenus = adminMenuMapper.selectAll();
        }else {
            adminMenus = adminMenuMapper.selectList(uid);
        }

        return null;
    }

    @Override
    public int addMenus(List<Long> ids) {
        return 0;
    }

    @Override
    public int cancelMenus(List<Long> ids) {
        return 0;
    }
}
