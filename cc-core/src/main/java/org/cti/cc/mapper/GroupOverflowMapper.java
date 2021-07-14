package org.cti.cc.mapper;

import org.cti.cc.entity.GroupOverflow;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.po.GroupOverflowPo;

import java.util.List;

public interface GroupOverflowMapper extends BaseMapper<GroupOverflow> {


    /**
     *
     * @param groupId
     * @return
     */
    List<GroupOverflowPo> selectByOverflow(Long groupId);
}