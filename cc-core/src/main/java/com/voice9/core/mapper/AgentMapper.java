package com.voice9.core.mapper;

import com.voice9.core.entity.Agent;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.AgentInfo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询坐席长时间在线未改变状态
     *
     * @param params
     * @return
     */
    List<Agent> selectAgentLongOnline(Map<String, Object> params);

    /**
     * 企业坐席数
     *
     * @param companyId
     * @return
     */
    Integer agentSize(Long companyId);

    /**
     *
     * @param params
     * @return
     */
    Integer agentNum(Map<String , Object> params);

    /**
     * @param companyId
     * @param ids
     * @return
     */
    List<Agent> selectByIds(@Param("companyId") Long companyId, @Param("ids") List<Long> ids);
}