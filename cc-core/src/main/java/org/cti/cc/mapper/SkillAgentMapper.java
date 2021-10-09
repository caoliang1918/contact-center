package org.cti.cc.mapper;

import org.apache.ibatis.annotations.Param;
import org.cti.cc.entity.SkillAgent;
import org.cti.cc.mapper.base.BaseMapper;
import org.cti.cc.vo.AgentBindSkill;

import java.util.List;

public interface SkillAgentMapper extends BaseMapper<SkillAgent> {

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(List<SkillAgent> list);


    /**
     * 查询坐席技能
     *
     * @param id
     * @return
     */
    List<SkillAgent> selectByAgent(Long id);

    /**
     * 删除坐席技能
     *
     * @param agentBindSkill
     * @return
     */
    int deleteSkillAgent(AgentBindSkill agentBindSkill);

    /**
     * 技能下关联的坐席
     *
     * @param companyId
     * @param id
     * @return
     */
    List<SkillAgent> selectBySkill(@Param("companyId") Long companyId,@Param("id") Long id);
}