package com.voice9.api.service.impl;

import com.voice9.api.service.CompanyService;
import com.voice9.core.entity.*;
import org.apache.commons.lang3.RandomStringUtils;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.page.Page;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.CompanyInfo;
import com.voice9.core.po.GroupInfo;
import com.voice9.core.po.GroupOverFlow;
import com.voice9.core.vo.CompanyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.voice9.api.exception.BusinessException;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/3/22
 */

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {


    @Override
    BaseMapper<Company> baseMapper() {
        return companyMapper;
    }

    @Override
    public int addCompany(CompanyVo companyVo) {
        Company company = new Company();
        BeanUtils.copyProperties(companyVo, company);
        company.setSecretKey(RandomStringUtils.randomAlphabetic(16));
        company.setSecretType(2);
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
    public List<CompanyInfo> getCompanyList(Map<String, Object> params) {
        return companyMapper.selectCompanyInfoList(params);
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
