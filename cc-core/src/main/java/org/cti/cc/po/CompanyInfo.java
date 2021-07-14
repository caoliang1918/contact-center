package org.cti.cc.po;

import org.cti.cc.entity.Company;
import org.cti.cc.entity.CompanyDisplay;
import org.cti.cc.entity.RouteGroup;

import java.util.HashMap;
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
    private Map<Long, VdnCodePo> vdnCodeMap ;


    /**
     * 企业路由字冠集合
     */
    private Map<String, RouteGroupPo> routeGroupMap;


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
}
