package com.voice9.core.mapper;

import com.voice9.core.entity.RouteGetway;
import com.voice9.core.mapper.base.BaseMapper;

import java.util.List;

public interface RouteGetwayMapper extends BaseMapper<RouteGetway> {

    /**
     * 批量查询
     *
     * @param ids
     * @return
     */
    List<RouteGetway> selectByIds(List<Long> ids);

    /**
     * 通过网关组来查询网关
     *
     * @param id
     * @return
     */
    List<RouteGetway> selectByGroupId(Long id);
}