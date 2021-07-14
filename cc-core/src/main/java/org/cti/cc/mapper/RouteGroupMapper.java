package org.cti.cc.mapper;

import org.cti.cc.entity.RouteGroup;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.RouteGroupPo;

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