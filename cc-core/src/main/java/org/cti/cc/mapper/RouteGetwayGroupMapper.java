package org.cti.cc.mapper;

import org.cti.cc.entity.RouteGetwayGroup;
import org.cti.cc.mapper.base.BaseMapper;

public interface RouteGetwayGroupMapper extends BaseMapper<RouteGetwayGroup> {


    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int deleteByRoutegroup(Long id);
}