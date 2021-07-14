package org.cti.cc.mapper;

import org.cti.cc.entity.OverflowFront;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface OverflowFrontMapper extends BaseMapper<OverflowFront> {

    /**
     *
     * @param overflowId
     * @return
     */
    List<OverflowFront> selectByOverflowId(Long overflowId);

}