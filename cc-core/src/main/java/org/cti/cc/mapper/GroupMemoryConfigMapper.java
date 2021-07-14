package org.cti.cc.mapper;

import org.cti.cc.entity.GroupMemoryConfig;
import org.cti.cc.mapper.base.BaseMapper;

public interface GroupMemoryConfigMapper extends BaseMapper<GroupMemoryConfig> {

    GroupMemoryConfig selectByGroupId(Long groupId);
}