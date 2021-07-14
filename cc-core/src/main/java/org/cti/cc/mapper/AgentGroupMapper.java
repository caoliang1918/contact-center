package org.cti.cc.mapper;

import org.cti.cc.entity.AgentGroup;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface AgentGroupMapper extends BaseMapper<AgentGroup> {

    /**
     * @param agentId
     * @return
     */
    List<Long> selectByAgent(Long agentId);

    /**
     * @param groupId
     * @return
     */
    List<Long> selectByGroup(Long groupId);

}