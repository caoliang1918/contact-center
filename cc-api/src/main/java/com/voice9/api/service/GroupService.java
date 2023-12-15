package com.voice9.api.service;

import com.voice9.core.entity.AgentGroup;
import com.voice9.core.entity.Group;
import com.voice9.core.po.GroupInfo;
import com.voice9.core.vo.GroupInfoVo;

import java.util.List;

public interface GroupService extends BaseService<Group> {


    /**
     * 技能组详情
     *
     * @param companyId
     * @param id
     * @return
     */
    GroupInfo getGroupInfo(Long companyId, Long id);

    /**
     * 增加或修改技能组
     *
     * @param groupInfoVo
     * @return
     */
    int saveOrUpdateGroup(GroupInfoVo groupInfoVo);


    /**
     * 删除技能组
     *
     * @param companyId
     * @param id
     * @return
     */
    int deleteGroup(Long companyId, Long id);

    /**
     * 技能组下坐席
     *
     * @param companyId
     * @param groupId
     * @return
     */
    List<AgentGroup> groupAgentList(Long companyId, Long groupId);
}
