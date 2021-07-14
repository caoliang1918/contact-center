package org.cti.cc.mapper;

import org.cti.cc.entity.GroupStrategyExp;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface GroupStrategyExpMapper extends BaseMapper<GroupStrategyExp> {

    /**
     *
     * @param groupId
     * @return
     */
    List<GroupStrategyExp> selectList(Long groupId);
}