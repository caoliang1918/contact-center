package org.voice9.cc.service;

import com.voice9.core.entity.Group;
import com.voice9.core.po.GroupInfo;

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
