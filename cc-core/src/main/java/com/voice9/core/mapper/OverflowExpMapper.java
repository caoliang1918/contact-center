package com.voice9.core.mapper;

import com.voice9.core.entity.OverflowExp;
import com.voice9.core.mapper.base.BaseMapper;

import java.util.List;

public interface OverflowExpMapper extends BaseMapper<OverflowExp> {

    /**
     * @param overflowId
     * @return
     */
    List<OverflowExp> selectByOverflowId(Long overflowId);
}