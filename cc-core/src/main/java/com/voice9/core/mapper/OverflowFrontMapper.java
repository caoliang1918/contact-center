package com.voice9.core.mapper;

import com.voice9.core.entity.OverflowFront;
import com.voice9.core.mapper.base.BaseMapper;

import java.util.List;

public interface OverflowFrontMapper extends BaseMapper<OverflowFront> {

    /**
     *
     * @param overflowId
     * @return
     */
    List<OverflowFront> selectByOverflowId(Long overflowId);

}