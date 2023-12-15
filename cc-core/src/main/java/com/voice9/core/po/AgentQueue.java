package com.voice9.core.po;

/**
 * Create by caoliang on 2020/11/10
 * <p>
 * 坐席属性队列
 */
public class AgentQueue implements Comparable<Long> {

    private Long id;

    private String agentKey;

    public AgentQueue(Long id, String agentKey) {
        this.id = id;
        this.agentKey = agentKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    @Override
    public int compareTo(Long o) {
        return this.id.compareTo(o);
    }

}
