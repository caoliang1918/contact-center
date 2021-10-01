package org.zhongweixian.api.service;

import org.cti.cc.entity.Skill;
import org.cti.cc.po.SkillInfo;

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
}
