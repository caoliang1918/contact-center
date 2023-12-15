package com.voice9.api.service;

import com.voice9.core.entity.Skill;
import com.voice9.core.po.SkillInfo;
import com.voice9.core.vo.SkillAgentVo;
import com.voice9.core.vo.SkillAgentsVo;
import com.voice9.core.vo.SkillVo;

/**
 * Created by caoliang on 2021/10/1
 */
public interface SkillService extends BaseService<Skill> {

    /**
     * 技能详情
     *
     * @param companyId
     * @param id
     * @return
     */
    SkillInfo skillInfo(Long companyId, Long id);

    /**
     * 新增或修改技能
     *
     * @param skillVo
     */
    int saveOrUpdateSkill(SkillVo skillVo);

    /**
     * 删除技能
     *
     * @param skill
     * @return
     */
    int deleteSkill(Skill skill);


    /**
     * 新增坐席技能
     *
     * @param skillAgentsVo
     * @return
     */
    int addSkillAgent(SkillAgentsVo skillAgentsVo);

    /**
     * 删除坐席技能
     *
     * @param skillAgentsVo
     * @return
     */
    int deleteSkippAgent(SkillAgentsVo skillAgentsVo);


    /**
     * @param skillAgent
     * @return
     */
    int updateSkillAgent(SkillAgentVo skillAgent);


}
