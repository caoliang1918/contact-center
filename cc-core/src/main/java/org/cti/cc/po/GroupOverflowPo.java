package org.cti.cc.po;

/**
 * Created by caoliang on 2020/11/25
 */
public class GroupOverflowPo extends GroupOverFlow {

    /**
     * 技能组ID
     */
    private Long groupId;

    /**
     *
     */
    private Long overflowId;

    /**
     * 优先级
     */
    private Integer levelValue;


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getOverflowId() {
        return overflowId;
    }

    public void setOverflowId(Long overflowId) {
        this.overflowId = overflowId;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }

}
