package org.zhongweixian.cc.service;

import org.cti.cc.entity.GroupMemory;

/**
 * Created by caoliang on 2021/8/14
 */
public interface GroupMemoryService extends BaseService<GroupMemory>{

    /**
     * 查询指定时间内的电话记录
     *
     * @param groupMemory
     * @return
     */
    GroupMemory selectByGroup(GroupMemory groupMemory);
}
