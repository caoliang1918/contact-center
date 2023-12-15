package com.voice9.core.mapper;

import com.voice9.core.entity.SkillGroup;
import com.voice9.core.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkillGroupMapper extends BaseMapper<SkillGroup> {


    /**
     * @param list
     * @return
     */
    int batchInsert(List<SkillGroup> list);


    /**
     * 查询技能组技能
     *
     * @param companyId 企业id
     * @param id        技能组id
     * @return
     */
    List<SkillGroup> selectByGroup(@Param("companyId") Long companyId, @Param("id") Long id);


    /**
     * 查询技能组技能
     *
     * @param companyId 企业id
     * @param id        技能id
     * @return
     */
    List<SkillGroup> selectBySkill(@Param("companyId") Long companyId, @Param("id") Long id);
}