package com.voice9.core.mapper;

import com.voice9.core.entity.RouteGetwayGroup;
import com.voice9.core.mapper.base.BaseMapper;

public interface RouteGetwayGroupMapper extends BaseMapper<RouteGetwayGroup> {


    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int deleteByRoutegroup(Long id);
}