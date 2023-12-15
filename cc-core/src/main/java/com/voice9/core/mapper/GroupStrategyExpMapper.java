package com.voice9.core.mapper;

import com.voice9.core.entity.GroupStrategyExp;
import com.voice9.core.mapper.base.BaseMapper;

import java.util.List;

public interface GroupStrategyExpMapper extends BaseMapper<GroupStrategyExp> {

    /**
     *
     * @param groupId
     * @return
     */
    List<GroupStrategyExp> selectList(Long groupId);
}