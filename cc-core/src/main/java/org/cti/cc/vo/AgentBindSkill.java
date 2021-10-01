package org.cti.cc.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by caoliang on 2021/5/10
 */
public class AgentBindSkill {

    /**
     * 企业id
     */
    private Long companyId;

    @NotNull(message = "技能id不能为空")
    private Long skillId;

    @NotNull(message = "技能等级不能为空")
    @Range(min = 1, max = 100, message = "技能等级在0-100之间")
    private Integer rankValue;

    @NotNull(message = "坐席不能为空")
    private List<Long> agentIds;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Integer getRankValue() {
        return rankValue;
    }

    public void setRankValue(Integer rankValue) {
        this.rankValue = rankValue;
    }

    public List<Long> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<Long> agentIds) {
        this.agentIds = agentIds;
    }
}
