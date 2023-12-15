package com.voice9.api.service.impl;


import com.voice9.core.entity.*;
import com.voice9.core.mapper.*;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.GroupInfo;
import com.voice9.core.vo.GroupInfoVo;
import com.voice9.core.vo.SkillGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.voice9.api.exception.BusinessException;
import com.voice9.api.service.GroupService;

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
    private AgentGroupMapper agentGroupMapper;

    @Autowired
    private SkillGroupMapper skillGroupMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private VdnConfigMapper vdnScheduleMapper;

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
        Map<String, Object> params = new HashMap<>(4);
        params.put("companyId", groupInfoVo.getCompanyId());
        params.put("name", groupInfoVo.getName());
        List<Group> groups = groupMapper.selectListByMap(params);
        //判断是否重复
        if (!CollectionUtils.isEmpty(groups)) {
            if (groupInfoVo.getId() == null ||
                    (groupInfoVo.getId() != null && !groupInfoVo.getId().equals(groups.get(0).getId()))) {
                throw new BusinessException(ErrorCode.DUPLICATE_EXCEPTION);
            }
        }

        //技能组技能不能超过10个
        if (groupInfoVo.getSkillList().size() > 10) {
            throw new BusinessException(ErrorCode.GROUP_SKILL_OVER_LIMIT);
        }
        Group group = new Group();
        BeanUtils.copyProperties(groupInfoVo, group);
        if (groupInfoVo.getId() == null) {
            //判断技能组是否超过限制
            Company company = companyMapper.selectByPrimaryKey(groupInfoVo.getCompanyId());
            params.remove("name");
            Integer count = groupMapper.selectCountByMap(params);
            if (count > company.getGroupLimit()) {
                throw new BusinessException(ErrorCode.GROUP_OVER_LIMIT);
            }
            group.setCts(Instant.now().getEpochSecond());
            groupMapper.insertSelective(group);
            Long id = groupMapper.selectByName(groupInfoVo.getCompanyId(), groupInfoVo.getName());
            group.setId(id);
            //技能组技能关联
            List<SkillGroup> skillGroupList = new ArrayList<>();
            for (SkillGroupVo skillGroupVo : groupInfoVo.getSkillList()) {
                SkillGroup skillGroup = new SkillGroup();
                BeanUtils.copyProperties(skillGroupVo, skillGroup);
                skillGroup.setGroupId(group.getId());
                skillGroup.setCompanyId(groupInfoVo.getCompanyId());
                skillGroup.setCts(Instant.now().getEpochSecond());
                skillGroup.setStatus(1);
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
        groupMapper.updateByPrimaryKeySelective(group);
        //排队策略


        //溢出策略

        return 0;
    }

    @Override
    public int deleteGroup(Long companyId, Long id) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("companyId", companyId);
        params.put("id", id);
        List<Group> groups = groupMapper.selectListByMap(params);
        //是否存在
        if (CollectionUtils.isEmpty(groups)) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST);
        }
        //技能组绑定了不能删
        params.put("routeType", 1);
        params.put("routeValue", id);
        List<VdnConfig> vdnSchedules = vdnScheduleMapper.selectListByMap(params);
        if (!CollectionUtils.isEmpty(vdnSchedules)) {
            throw new BusinessException(ErrorCode.DATA_NOT_EXIST, "技能组已经被vdn引用");
        }

        //删除当前技能组与坐席关系
        agentGroupMapper.deleteByGroup(id);

        Group group = new Group();
        group.setId(groups.get(0).getId());
        group.setStatus(0);
        group.setUts(Instant.now().getEpochSecond());
        group.setName(groups.get(0).getName() + randomDelete());
        return groupMapper.updateByPrimaryKeySelective(group);
    }

    @Override
    public List<AgentGroup> groupAgentList(Long companyId, Long groupId) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId);
        params.put("groupId", groupId);
        return agentGroupMapper.selectListByMap(params);
    }
}
