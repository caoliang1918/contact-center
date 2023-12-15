package com.voice9.core.po;

import com.voice9.core.entity.Agent;
import com.voice9.core.entity.AgentSip;

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
