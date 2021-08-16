package org.zhongweixian.cc.service.impl;

import org.cti.cc.entity.GroupMemory;
import org.cti.cc.mapper.GroupMemoryMapper;
import org.cti.cc.mapper.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhongweixian.cc.service.GroupMemoryService;

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
