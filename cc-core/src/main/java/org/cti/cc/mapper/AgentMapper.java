package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.Agent;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.AgentInfo;

import java.util.List;
import java.util.Map;

public interface AgentMapper extends BaseMapper<Agent> {
    /**
     * @param agentKey
     * @return
     */
    Agent selectAgent(String agentKey);


    int addAgent(Agent agent);

    /**
     * 查询坐席
     *
     * @param params
     * @return
     */
    AgentInfo selectAgentInfo(Map<String, Object> params);


    /**
     * 批量插入坐席
     *
     * @param agentList
     * @return
     */
    int batchInsert(List<Agent> agentList);


    /**
     * 删除坐席
     *
     * @param agent
     * @return
     */
    int deleteAgent(Agent agent);


    Agent selectSimpleAgent(Long id);
}