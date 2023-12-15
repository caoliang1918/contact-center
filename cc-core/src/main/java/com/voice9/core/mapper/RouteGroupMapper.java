package com.voice9.core.mapper;

import com.voice9.core.entity.RouteGroup;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.RouteGroupPo;

public interface RouteGroupMapper extends BaseMapper<RouteGroup> {


    /**
     *
     * @param id
     * @return
     */
    RouteGroupPo selectById(Long id);

    /**
     *
     * @param getwayId
     * @return
     */
    RouteGroupPo selectByGetwayId(Long getwayId);
}