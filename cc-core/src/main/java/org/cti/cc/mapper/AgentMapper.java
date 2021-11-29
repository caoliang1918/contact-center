package org.cti.cc.mapper;

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

    /**
     * 通过sip查询绑定的坐席
     *
     * @param sip
     * @return
     */
    Agent selectAgentBySip(String sip);

    List<Agent> selectAgentOnline(Map<String, Object> params);

    /**
     * 企业坐席数
     *
     * @param companyId
     * @return
     */
    Integer agentSize(Long companyId);
}