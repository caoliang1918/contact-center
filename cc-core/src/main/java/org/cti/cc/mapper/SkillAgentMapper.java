package org.cti.cc.mapper;

import org.cti.cc.entity.SkillAgent;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface SkillAgentMapper extends BaseMapper<SkillAgent> {

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(List<SkillAgent> list);
}