package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.RouteCall;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.RouteCallInfo;

import java.util.List;
import java.util.Map;

public interface RouteCallMapper extends BaseMapper<RouteCallInfo> {

    /**
     * 查询分页
     *
     * @param params
     * @return
     */
    List<RouteCall> selectList(Map<String, Object> params);


    /**
     * 新增
     *
     * @param routeCall
     * @return
     */
    int insertSelective(RouteCall routeCall);

    /**
     * 修改
     *
     * @param routeCall
     * @return
     */
    int updateByPrimaryKeySelective(RouteCall routeCall);
}