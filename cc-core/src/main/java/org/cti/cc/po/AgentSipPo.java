package org.cti.cc.po;

import org.cti.cc.entity.Agent;
import org.cti.cc.entity.AgentSip;

/**
 * Created by caoliang on 2020/06/06
 */
public class AgentSipPo extends AgentSip {

    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
