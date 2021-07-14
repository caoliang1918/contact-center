package org.cti.cc.mapper;

import org.cti.cc.entity.SkillGroup;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface SkillGroupMapper extends BaseMapper<SkillGroup> {


    /**
     *
     * @param list
     * @return
     */
    int batchInsert(List<SkillGroup> list);
}