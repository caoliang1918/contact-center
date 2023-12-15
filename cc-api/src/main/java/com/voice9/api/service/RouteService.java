package com.voice9.api.service;

import com.github.pagehelper.PageInfo;
import com.voice9.core.entity.RouteGetway;
import com.voice9.core.entity.RouteGroup;
import com.voice9.core.po.RouteCallInfo;
import com.voice9.core.po.RouteGroupPo;
import com.voice9.core.vo.RouteCallVo;
import com.voice9.core.vo.RouteGetwayVo;
import com.voice9.core.vo.RouteGroupVo;

import java.util.Map;

public interface RouteService extends BaseService<RouteCallInfo> {

    /**
     * 网关
     *
     * @param params
     * @return
     */
    PageInfo<RouteGetway> findRouteGetwayByPage(Map<String, Object> params);

    /**
     * 获取网关详情
     *
     * @param id
     * @return
     */
    RouteGetway findRoutewayById(Long id);

    /**
     * @param id
     * @return
     */
    int delRoutewayById(Long id);


    /**
     * 添加/修改网关
     *
     * @param routeGetwayVo
     * @return
     */
    int saveOrUpdateRouteGetway(RouteGetwayVo routeGetwayVo);

    /**
     * 网关组列表
     *
     * @param params
     * @return
     */
    PageInfo<RouteGroup> findRouteGroupByPage(Map<String, Object> params);


    /**
     * 查询网关组详情
     *
     * @param id
     * @return
     */
    RouteGroupPo findRouteGroup(Long id);


    /**
     * 网关列表
     *
     * @param params
     * @return
     */
    PageInfo<RouteCallInfo> findRouteCallByPage(Map<String, Object> params);

    /**
     * 获取字冠详情
     *
     * @param params
     * @return
     */
    RouteCallInfo findRouteCall(Map<String, Object> params);

    /**
     * 添加字冠
     *
     * @param routeCallVo
     * @return
     */
    int saveOrUpdateRouteCall(RouteCallVo routeCallVo);

    /**
     * 添加网关组
     *
     * @param routeGroupVo
     * @return
     */
    int saveOrUpdateRouteGroup(RouteGroupVo routeGroupVo);


    /**
     * 删除字冠
     *
     * @param companyId
     * @param id
     * @return
     */
    int deleteRouteCall(Long companyId, Long id);

    /**
     * 删除网关组
     *
     * @param id
     * @return
     */
    int deleteRouteGroup(Long id);

}
