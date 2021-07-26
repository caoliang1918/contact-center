package org.zhongweixian.cc.service.impl;


import org.cti.cc.entity.Company;
import org.cti.cc.entity.Group;
import org.cti.cc.entity.SkillGroup;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.mapper.CompanyMapper;
import org.cti.cc.mapper.GroupMapper;
import org.cti.cc.mapper.SkillGroupMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.GroupInfo;
import org.cti.cc.vo.GroupInfoVo;
import org.cti.cc.vo.SkillGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.exception.BusinessException;
import org.zhongweixian.cc.service.GroupService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private SkillGroupMapper skillGroupMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    BaseMapper<Group> baseMapper() {
        return groupMapper;
    }

    @Override
    public GroupInfo getGroupInfo(Long companyId, Long id) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("companyId", companyId);
        params.put("id", id);
        return groupMapper.selectGroupInfo(params);
    }


    @Override
    public int saveOrUpdateGroup(GroupInfoVo groupInfoVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", groupInfoVo.getCompanyId());
        params.put("name" , groupInfoVo.getName());
        List<Group> groups = groupMapper.selectListByMap(params);
        //判断是否重复
        if (!CollectionUtils.isEmpty(groups)) {
            if (groupInfoVo.getId() == null || !groupInfoVo.getId().equals(groups.get(0).getId())) {
                throw new BusinessException(ErrorCode.DUPLICATE_EXCEPTION);
            }
        }

        /**
         * 技能组技能不能超过10个
         */
        if (groupInfoVo.getSkillList().size() > 10) {
            throw new BusinessException(ErrorCode.GROUP_SKILL_OVER_LIMIT);
        }
        Group group = new Group();
        BeanUtils.copyProperties(groupInfoVo, group);
        if (groupInfoVo.getId() == null) {
            //判断技能组是否超过限制
            Company company = companyMapper.selectByPrimaryKey(groupInfoVo.getCompanyId());
            params.remove("name");
            Long count = groupMapper.selectCountByMap(params);
            if (count > company.getGroupLimit()) {
                throw new BusinessException(ErrorCode.GROUP_OVER_LIMIT);
            }
            group.setCts(Instant.now().getEpochSecond());
            groupMapper.insertSelective(group);
            //技能组技能关联
            List<SkillGroup> skillGroupList = new ArrayList<>();
            for (SkillGroupVo skillGroupVo : groupInfoVo.getSkillList()) {
                SkillGroup skillGroup = new SkillGroup();
                BeanUtils.copyProperties(skillGroupVo, skillGroup);
                skillGroup.setGroupId(group.getId());
                skillGroup.setCompanyId(groupInfoVo.getCompanyId());
                skillGroup.setCts(Instant.now().getEpochSecond());
                skillGroupList.add(skillGroup);
            }
            skillGroupMapper.batchInsert(skillGroupList);

            //坐席空闲策略

            return 1;
        }

        params.put("id", groupInfoVo.getId());
        GroupInfo exist = groupMapper.selectGroupInfo(params);
        if (exist == null) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }

        group.setUts(Instant.now().getEpochSecond());
        groupMapper.updateByPrimaryKey(group);
        //排队策略


        //溢出策略

        return 0;
    }

    @Override
    public int deleteGroup(Long companyId, Long id) {
        return 0;
    }
}
