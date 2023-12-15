package com.voice9.core.po;

import com.voice9.core.entity.Company;
import com.voice9.core.entity.CompanyDisplay;
import com.voice9.core.entity.CompanyStat;

import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2020/11/6
 */
public class CompanyInfo extends Company {

    /**
     * 企业技能组
     */
    private List<Long> groupIds;


    /**
     * 企业号码池
     */
    private List<CompanyDisplay> companyDisplays;

    /**
     * vdnId - vdnCode
     */
    private Map<Long, VdnCodePo> vdnCodeMap;


    /**
     * 企业路由字冠集合
     */
    private Map<String, RouteGroupPo> routeGroupMap;

    /**
     * 企业报表统计
     */
    private List<CompanyStat> companyStats;

    /**
     * 企业坐席数量
     */
    private Integer agentSize;


    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }

    public List<CompanyDisplay> getCompanyDisplays() {
        return companyDisplays;
    }

    public void setCompanyDisplays(List<CompanyDisplay> companyDisplays) {
        this.companyDisplays = companyDisplays;
    }

    public Map<Long, VdnCodePo> getVdnCodeMap() {
        return vdnCodeMap;
    }

    public void setVdnCodeMap(Map<Long, VdnCodePo> vdnCodeMap) {
        this.vdnCodeMap = vdnCodeMap;
    }

    public Map<String, RouteGroupPo> getRouteGroupMap() {
        return routeGroupMap;
    }

    public void setRouteGroupMap(Map<String, RouteGroupPo> routeGroupMap) {
        this.routeGroupMap = routeGroupMap;
    }

    public List<CompanyStat> getCompanyStats() {
        return companyStats;
    }

    public void setCompanyStats(List<CompanyStat> companyStats) {
        this.companyStats = companyStats;
    }

    public Integer getAgentSize() {
        return agentSize;
    }

    public void setAgentSize(Integer agentSize) {
        this.agentSize = agentSize;
    }
}
