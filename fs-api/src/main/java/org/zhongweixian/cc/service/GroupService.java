package org.zhongweixian.cc.service;

import org.cti.cc.entity.Group;
import org.cti.cc.po.GroupInfo;

import java.util.List;

/**
 * Create by caoliang on 2020/10/28
 */
public interface GroupService extends BaseService<Group> {

    List<GroupInfo> getGroupByConpany(Long companyId);

    /**
     * 初始化技能组排队策略
     * @param groupInfo
     */
    void initGroupStrategy(GroupInfo groupInfo);
}
