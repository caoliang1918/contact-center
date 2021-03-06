package org.cti.cc.mapper;

import org.cti.cc.entity.GroupMemory;
import org.cti.cc.mapper.base.BaseMapper;

public interface GroupMemoryMapper extends BaseMapper<GroupMemory> {

    /**
     * 查询指定时间内的电话记录
     *
     * @param groupMemory
     * @return
     */
    GroupMemory selectByGroup(GroupMemory groupMemory);

}