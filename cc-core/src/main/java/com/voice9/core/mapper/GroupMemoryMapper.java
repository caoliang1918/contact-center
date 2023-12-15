package com.voice9.core.mapper;

import com.voice9.core.entity.GroupMemory;
import com.voice9.core.mapper.base.BaseMapper;

public interface GroupMemoryMapper extends BaseMapper<GroupMemory> {

    /**
     * 查询指定时间内的电话记录
     *
     * @param groupMemory
     * @return
     */
    GroupMemory selectByGroup(GroupMemory groupMemory);

}