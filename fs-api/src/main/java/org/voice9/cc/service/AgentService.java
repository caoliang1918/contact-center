package org.voice9.cc.service;

import com.voice9.core.entity.Agent;
import com.voice9.core.po.AgentInfo;

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
    void syncAgentStateMessage(AgentInfo agentInfo);

    /**
     *
     * @param sip
     * @return
     */
    Agent getAgentBySip(String sip);

    void saveAgentLog(AgentInfo agentInfo);

    /**
     *
     * @return
     */
    String getTokenKey();

}
