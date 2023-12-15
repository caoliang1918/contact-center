package com.voice9.api.service;

import com.voice9.core.entity.*;
import com.voice9.core.page.Page;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.CompanyInfo;
import com.voice9.core.po.GroupInfo;
import com.voice9.core.po.GroupOverFlow;
import com.voice9.core.vo.CompanyVo;

import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/3/22
 */
public interface CompanyService extends BaseService<Company> {

    /**
     * 添加企业
     *
     * @param companyVo
     * @return
     */
    int addCompany(CompanyVo companyVo);

    /**
     * 企业信息
     *
     * @param id
     * @return
     */
    CompanyInfo getCompanyInfo(Long id);

    /**
     * 查询企业
     *
     * @param params
     * @return
     */
    List<CompanyInfo> getCompanyList(Map<String, Object> params);

    /**
     * 修改企业
     *
     * @param company
     * @return
     */
    int updateCompany(Company company);

    /**
     * 删除企业
     *
     * @param id
     * @return
     */
    int deleteCompany(Long id);


    /**
     * 添加技能组
     *
     * @param groupInfo
     * @return
     */
    int addGroup(GroupInfo groupInfo);

    /**
     * 技能组详情
     *
     * @param id
     * @param companyId
     * @return
     */
    GroupInfo getGroupInfo(Long id, Long companyId);

    /**
     * 修改技能组
     *
     * @param groupInfo
     * @return
     */
    int updateGroup(GroupInfo groupInfo);

    /**
     * 删除技能组
     *
     * @param id
     * @param companyId
     * @return
     */
    int deleteGroup(Long id, Long companyId);

    /**
     * 技能组列表
     *
     * @param params
     * @return
     */
    Page<GroupInfo> groupList(Map<String, Object> params);

    /**
     * 添加坐席
     *
     * @param agent
     * @return
     */
    int addAgent(Agent agent);

    /**
     * 坐席详情
     *
     * @param id
     * @param companyId
     * @return
     */
    AgentInfo getAgentInfo(Long id, Long companyId);

    /**
     * 修改坐席
     *
     * @param agent
     * @return
     */
    int updateAgent(Agent agent);


    /**
     * 删除坐席
     *
     * @param id
     * @param companyId
     * @return
     */
    int deleteAgent(Long id, Long companyId);

    /**
     * 坐席列表
     *
     * @param params
     * @return
     */
    Page<AgentInfo> AgentList(Map<String, Object> params);


    /**
     * 添加技能
     *
     * @param skill
     * @return
     */
    int addSkill(Skill skill);

    /**
     * 修改技能
     *
     * @param skill
     * @return
     */
    int updateSkill(Skill skill);

    /**
     * 删除技能
     *
     * @param id
     * @param companyId
     * @return
     */
    int deleteSkill(Long id, Long companyId);

    /**
     * 技能列表
     *
     * @param params
     * @return
     */
    Page<Skill> skillList(Map<String, Object> params);

    /**
     * 技能下坐席列表
     *
     * @param params
     * @return
     */
    Page<SkillAgent> skillAgentList(Map<String, Object> params);

    /**
     * 技能下技能组列表
     *
     * @param params
     * @return
     */
    Page<SkillGroup> skillGroupList(Map<String, Object> params);

    /**
     * 排队溢出策略
     *
     * @param params
     * @return
     */
    Page<GroupOverFlow> overFlowList(Map<String, Object> params);

    /**
     * 获取排队溢出策略
     *
     * @param id
     * @param companyId
     * @return
     */
    GroupOverFlow getGroupOverFlow(Long id, Long companyId);

}
