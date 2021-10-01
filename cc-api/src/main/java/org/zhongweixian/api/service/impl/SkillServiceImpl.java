package org.zhongweixian.api.service.impl;

import org.cti.cc.entity.Skill;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.mapper.SkillAgentMapper;
import org.cti.cc.mapper.SkillGroupMapper;
import org.cti.cc.mapper.SkillMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.SkillInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhongweixian.api.exception.BusinessException;
import org.zhongweixian.api.service.SkillService;

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
}
