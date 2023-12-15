package com.voice9.core.mapper;

import com.voice9.core.entity.GroupMemoryConfig;
import com.voice9.core.mapper.base.BaseMapper;

public interface GroupMemoryConfigMapper extends BaseMapper<GroupMemoryConfig> {

    GroupMemoryConfig selectByGroupId(Long groupId);
}