package org.zhongweixian.api.service;

import org.cti.cc.entity.Group;
import org.cti.cc.po.GroupInfo;
import org.cti.cc.vo.GroupInfoVo;

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
}
