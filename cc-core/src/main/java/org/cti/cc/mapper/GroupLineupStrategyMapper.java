package org.cti.cc.mapper;

import org.cti.cc.entity.GroupLineupStrategy;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.GroupLineupStrategyPo;

public interface GroupLineupStrategyMapper extends BaseMapper<GroupLineupStrategy> {

    /**
     * 技能组中坐席空闲策略
     * @param groupId
     * @return
     */
    GroupLineupStrategyPo selectByGroupId(Long groupId);
}