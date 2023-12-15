package com.voice9.core.mapper;

import com.voice9.core.entity.GroupAgentStrategy;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.GroupAgentStrategyPo;

public interface GroupAgentStrategyMapper extends BaseMapper<GroupAgentStrategy> {

    /**
     * 技能组中坐席空闲策略
     * @param groupId
     * @return
     */
    GroupAgentStrategyPo selectByGroupId(Long groupId);
}