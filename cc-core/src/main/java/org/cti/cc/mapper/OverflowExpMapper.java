package org.cti.cc.mapper;

import org.cti.cc.entity.OverflowExp;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface OverflowExpMapper extends BaseMapper<OverflowExp> {

    /**
     * @param overflowId
     * @return
     */
    List<OverflowExp> selectByOverflowId(Long overflowId);
}