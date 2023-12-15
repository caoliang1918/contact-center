package org.voice9.cc.service.impl;

import com.voice9.core.entity.GroupMemory;
import com.voice9.core.mapper.GroupMemoryMapper;
import com.voice9.core.mapper.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voice9.cc.service.GroupMemoryService;

/**
 * Created by caoliang on 2021/8/14
 */

@Service
public class GroupMemoryServiceImpl extends BaseServiceImpl<GroupMemory> implements GroupMemoryService {

    @Autowired
    private GroupMemoryMapper groupMemoryMapper;

    @Override
    BaseMapper<GroupMemory> baseMapper() {
        return groupMemoryMapper;
    }


    @Override
    public GroupMemory selectByGroup(GroupMemory groupMemory) {
        return groupMemoryMapper.selectByGroup(groupMemory);
    }
}
