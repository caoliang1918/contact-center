package org.zhongweixian.api.service;

import org.cti.cc.po.MenusPo;

import java.util.List;

/**
 * Created by caoliang on 2022/1/6
 */
public interface AdminService  {

    /**
     * 获取菜单
     *
     * @param uid
     * @return
     */
    List<MenusPo> getMenus(Long uid);

    /**
     * 授权菜单
     *
     * @param ids
     * @return
     */
    int addMenus(List<Long> ids);

    /**
     * 取消授权
     *
     * @param ids
     * @return
     */
    int cancelMenus(List<Long> ids);


}
