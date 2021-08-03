package org.cti.cc.mapper;

import org.cti.cc.entity.GroupAgentStrategy;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.GroupAgentStrategyPo;

public interface GroupAgentStrategyMapper extends BaseMapper<GroupAgentStrategy> {

    /**
     * 技能组中坐席空闲策略
     * @param groupId
     * @return
     */
    GroupAgentStrategyPo selectByGroupId(Long groupId);
}