package com.voice9.core.po;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2021/9/29
 * <p>
 * 技能组空闲坐席
 */
public class GroupAgentFree {
    /**
     * PK
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 技能组名称
     */
    private String name;

    /**
     * 技能组类型
     */
    private Integer groupType;

    /**
     * 空闲坐席
     */
    private List<String> freeAgents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public List<String> getFreeAgents() {
        return freeAgents;
    }

    public void setFreeAgents(List<String> freeAgents) {
        this.freeAgents = freeAgents;
    }
}
