package org.zhongweixian.api.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.cti.cc.entity.*;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.mapper.AgentMapper;
import org.cti.cc.mapper.CompanyMapper;
import org.cti.cc.mapper.GroupMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.page.Page;
import org.cti.cc.po.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhongweixian.api.exception.BusinessException;
import org.zhongweixian.api.service.CompanyService;
import org.zhongweixian.api.vo.CompanyVo;

import java.time.Instant;
import java.util.Map;

/**
 * Created by caoliang on 2021/3/22
 */

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Override
    BaseMapper<Company> baseMapper() {
        return companyMapper;
    }

    @Override
    public int addCompany(CompanyVo companyVo) {
        Company company = new Company();
        BeanUtils.copyProperties(companyVo, company);
        return companyMapper.insertSelective(company);
    }

    @Override
    public CompanyInfo getCompanyInfo(Long id) {
        CompanyInfo companyInfo = companyMapper.selectById(id);
        if (companyInfo == null || companyInfo.getStatus() == 0) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        return companyMapper.selectById(id);
    }

    @Override
    public int updateCompany(Company company) {
        return companyMapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public int deleteCompany(Long id) {
        Company company = getCompanyInfo(id);
        if (company == null || company.getStatus() == 0) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }

        company.setStatus(0);
        company.setUts(Instant.now().getEpochSecond());
        company.setName(company.getName() + "-del" + RandomStringUtils.randomAlphabetic(6));
        company.setCompanyCode(company.getCompanyCode() + "-del" + RandomStringUtils.randomAlphabetic(6));
        return companyMapper.deleteCompany(company);
    }


    @Override
    public int addGroup(GroupInfo groupInfo) {
        return 0;
    }

    @Override
    public GroupInfo getGroupInfo(Long id, Long companyId) {
        return null;
    }

    @Override
    public int updateGroup(GroupInfo groupInfo) {
        return 0;
    }

    @Override
    public int deleteGroup(Long id, Long companyId) {
        return 0;
    }

    @Override
    public Page<GroupInfo> groupList(Map<String, Object> params) {
        return null;
    }

    @Override
    public int addAgent(Agent agent) {
        return 0;
    }

    @Override
    public AgentInfo getAgentInfo(Long id, Long companyId) {
        return null;
    }

    @Override
    public int updateAgent(Agent agent) {
        return 0;
    }

    @Override
    public int deleteAgent(Long id, Long companyId) {
        return 0;
    }

    @Override
    public Page<AgentInfo> AgentList(Map<String, Object> params) {
        return null;
    }

    @Override
    public int addSkill(Skill skill) {
        return 0;
    }

    @Override
    public int updateSkill(Skill skill) {
        return 0;
    }

    @Override
    public int deleteSkill(Long id, Long companyId) {
        return 0;
    }

    @Override
    public Page<Skill> skillList(Map<String, Object> params) {
        return null;
    }

    @Override
    public Page<SkillAgent> skillAgentList(Map<String, Object> params) {
        return null;
    }

    @Override
    public Page<SkillGroup> skillGroupList(Map<String, Object> params) {
        return null;
    }

    @Override
    public Page<GroupOverFlow> overFlowList(Map<String, Object> params) {
        return null;
    }

    @Override
    public GroupOverFlow getGroupOverFlow(Long id, Long companyId) {
        return null;
    }


}
