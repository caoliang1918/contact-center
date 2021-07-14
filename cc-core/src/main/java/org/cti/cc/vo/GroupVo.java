package org.cti.cc.vo;

import org.cti.cc.entity.Group;
import org.cti.cc.entity.Skill;

import java.util.List;

/**
 * Created by caoliang on 2020/12/20
 */
public class GroupVo  extends Group {

    /**
     * 当前在线坐席
     */
    private List<String> onlineAgents;

    /**
     * 所有技能
     */
    private List<Skill> skills;

    public List<String> getOnlineAgents() {
        return onlineAgents;
    }

    public void setOnlineAgents(List<String> onlineAgents) {
        this.onlineAgents = onlineAgents;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
