package org.zhongweixian.cc.service;

import org.cti.cc.entity.Agent;
import org.cti.cc.po.AgentInfo;

import java.util.List;

/**
 * Create by caoliang on 2020/10/28
 */
public interface AgentService extends BaseService<Agent> {

    /**
     * @param agentKey
     * @return
     */
    AgentInfo getAgentInfo(String agentKey);

    /**
     * @param agentId
     * @return
     */
    List<Long> getAgentGroups(Long agentId);

    /**
     * @param agentId
     * @return
     */
    List<String> getAgentSips(Long agentId);


    /**
     * @param agentInfo
     */
    void sendAgentStateMessage(AgentInfo agentInfo);
}
