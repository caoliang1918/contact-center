package com.voice9.api.service.impl;

import com.voice9.api.exception.BusinessException;
import com.voice9.api.service.SkillService;
import com.voice9.core.entity.Agent;
import com.voice9.core.entity.Skill;
import com.voice9.core.entity.SkillAgent;
import com.voice9.core.entity.SkillGroup;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.mapper.AgentMapper;
import com.voice9.core.mapper.SkillAgentMapper;
import com.voice9.core.mapper.SkillGroupMapper;
import com.voice9.core.mapper.SkillMapper;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.SkillInfo;
import com.voice9.core.vo.SkillAgentVo;
import com.voice9.core.vo.SkillAgentsVo;
import com.voice9.core.vo.SkillVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2021/10/1
 */
@Service
public class SkillServiceImpl extends BaseServiceImpl<Skill> implements SkillService {

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private SkillAgentMapper skillAgentMapper;

    @Autowired
    private SkillGroupMapper skillGroupMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Override
    BaseMapper<Skill> baseMapper() {
        return skillMapper;
    }


    @Override
    public SkillInfo skillInfo(Long companyId, Long id) {
        Skill skill = skillMapper.selectById(companyId, id);
        if (skill == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        SkillInfo skillInfo = new SkillInfo();
        BeanUtils.copyProperties(skill, skillInfo);
        skillInfo.setSkillAgents(skillAgentMapper.selectBySkill(companyId, id));
        return skillInfo;
    }

    @Override
    public int saveOrUpdateSkill(SkillVo skillVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", skillVo.getCompanyId());
        params.put("name", skillVo.getName());
        List<Skill> skills = skillMapper.selectListByMap(params);
        if (!CollectionUtils.isEmpty(skills)) {
            if (skillVo.getId() == null || !skillVo.getId().equals(skills.get(0).getId())) {
                throw new BusinessException(ErrorCode.DUPLICATE_EXCEPTION, "技能名称已存在");
            }
        }

        Skill skill = new Skill();
        BeanUtils.copyProperties(skillVo, skill);
        if (skillVo.getId() == null) {
            skill.setCts(Instant.now().getEpochSecond());
            skill.setUts(skill.getCts());
            return skillMapper.insertSelective(skill);
        }
        skill.setUts(Instant.now().getEpochSecond());
        return skillMapper.updateByPrimaryKeySelective(skill);
    }

    @Override
    public int deleteSkill(Skill skill) {
        Skill entity = skillMapper.selectById(skill.getCompanyId(), skill.getId());
        if (entity == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        //先删除技能组关联
        List<SkillGroup> skillGroups = skillGroupMapper.selectBySkill(skill.getCompanyId(), skill.getId());
        if (!CollectionUtils.isEmpty(skillGroups)) {
            throw new BusinessException(ErrorCode.DATA_IS_USED, "当前技能绑定了技能组");
        }

        //再删除技能与坐席关联
        List<SkillAgent> skillAgents = skillAgentMapper.selectBySkill(skill.getCompanyId(), skill.getId());
        if (!CollectionUtils.isEmpty(skillAgents)) {
            throw new BusinessException(ErrorCode.DATA_IS_USED, "当前技能绑定了坐席");
        }


        skill.setName(entity.getName() + randomDelete());
        skill.setStatus(0);
        return skillMapper.updateByPrimaryKeySelective(skill);
    }

    @Override
    public int addSkillAgent(SkillAgentsVo agentBindSkill) {
        Skill skill = skillMapper.selectById(agentBindSkill.getCompanyId(), agentBindSkill.getSkillId());
        if (skill == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        if (CollectionUtils.isEmpty(agentBindSkill.getAgentIds())) {
            throw new BusinessException(ErrorCode.PARAMETER_ERROR);
        }
        //判断坐席是否不存在
        List<Agent> agents = agentMapper.selectByIds(agentBindSkill.getCompanyId(), agentBindSkill.getAgentIds());
        if (CollectionUtils.isEmpty(agents) || agents.size() != agentBindSkill.getAgentIds().size()) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST, "坐席数据不存在");
        }
        //避免坐席重复插入
        List<SkillAgent> existSkillAgent = skillAgentMapper.selectBySkill(agentBindSkill.getCompanyId(), agentBindSkill.getSkillId());
        if (!CollectionUtils.isEmpty(existSkillAgent)) {
            for (SkillAgent skillAgent : existSkillAgent) {
                if (agentBindSkill.getAgentIds().contains(skillAgent.getAgentId())) {
                    agentBindSkill.getAgentIds().remove(skillAgent.getAgentId());
                }
            }
        }
        if (CollectionUtils.isEmpty(agentBindSkill.getAgentIds())) {
            return 0;
        }

        Long cts = Instant.now().getEpochSecond();
        List<SkillAgent> skillAgents = new ArrayList<>();
        for (Long id : agentBindSkill.getAgentIds()) {
            SkillAgent skillAgent = new SkillAgent();
            skillAgent.setAgentId(id);
            skillAgent.setSkillId(agentBindSkill.getSkillId());
            skillAgent.setCompanyId(agentBindSkill.getCompanyId());
            skillAgent.setRankValue(agentBindSkill.getRankValue());
            skillAgent.setStatus(1);
            skillAgent.setCts(cts);
            skillAgents.add(skillAgent);
        }
        return skillAgentMapper.batchInsert(skillAgents);
    }

    @Override
    public int deleteSkippAgent(SkillAgentsVo skillAgentsVo) {
        return skillAgentMapper.deleteSkillAgent(skillAgentsVo);
    }

    @Override
    public int updateSkillAgent(SkillAgentVo skillAgent) {
        Skill skill = skillMapper.selectById(skillAgent.getCompanyId(), skillAgent.getId());
        if (skill == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        SkillAgent entity = new SkillAgent();
        entity.setSkillId(skillAgent.getId());
        entity.setAgentId(skillAgent.getAgentId());
        entity.setRankValue(skillAgent.getRankValue());
        entity.setUts(Instant.now().getEpochSecond());
        return skillAgentMapper.updateSkillAgent(entity);
    }
}
