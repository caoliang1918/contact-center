package com.voice9.core.mapper;

import com.voice9.core.entity.GroupOverflow;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.GroupOverflowPo;

import java.util.List;

public interface GroupOverflowMapper extends BaseMapper<GroupOverflow> {


    /**
     *
     * @param groupId
     * @return
     */
    List<GroupOverflowPo> selectByOverflow(Long groupId);
}