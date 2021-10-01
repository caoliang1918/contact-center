package org.cti.cc.po;

import org.cti.cc.entity.Skill;
import org.cti.cc.entity.SkillAgent;

import java.util.List;

/**
 * Created by caoliang on 2021/10/1
 */
public class SkillInfo extends Skill {

    /**
     * 技能中的坐席
     */
    private List<SkillAgent> skillAgents;

    public List<SkillAgent> getSkillAgents() {
        return skillAgents;
    }

    public void setSkillAgents(List<SkillAgent> skillAgents) {
        this.skillAgents = skillAgents;
    }
}
