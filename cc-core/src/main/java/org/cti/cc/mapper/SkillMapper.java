package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.Skill;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;

public interface SkillMapper extends BaseMapper<Skill> {

    /**
     * 查询技能组技能
     *
     * @param id
     * @return
     */
    List<Skill> selectByGroup(Long id);

    /**
     * 查询坐席技能
     *
     * @param id
     * @return
     */
    List<Skill> selectByAgent(Long id);

    /**
     * 查询技能
     *
     * @param companyId
     * @param id
     * @return
     */
    Skill selectById(@Param("id") Long id, @Param("companyId") Long companyId);
}