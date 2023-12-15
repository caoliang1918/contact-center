package com.voice9.core.po;

import com.voice9.core.entity.OverflowFront;
import com.voice9.core.strategy.LineupStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by caoliang on 2020/11/25
 */
public class GroupOverflowPo extends GroupOverFlow {

    /**
     * 技能组ID
     */
    private Long groupId;

    /**
     * 溢出策略id
     */
    private Long overflowId;

    /**
     * 优先级
     */
    private Integer levelValue;

    /**
     * 电话排队策略接口
     */
    private LineupStrategy lineupStrategy;

    /**
     * @param queueSize    队列长度
     * @param maxWaitTime  最大等待时长
     * @param callInAnswer 呼入应答数
     * @param callInTotal  呼入总数
     * @return
     */
    public boolean isAvailable(Integer queueSize, Integer maxWaitTime, Integer callInAnswer, Integer callInTotal) {
        boolean result = false;
        if (overflowFronts == null || overflowFronts.isEmpty()) {
            return result;
        }

        for (OverflowFront front : overflowFronts) {
            switch (front.getFrontType()) {
                case 1:
                    //1:队列长度;
                    result = compareCondition(queueSize, front);
                    break;

                case 2:
                    // 2:队列等待最大时长;
                    result = compareCondition(maxWaitTime, front);
                    break;

                case 3:
                    // 3:呼损率
                    int persent = 0;
                    if (callInAnswer > 0) {
                        persent = (int) (new BigDecimal(1).subtract(new BigDecimal(callInAnswer).divide(new BigDecimal(callInTotal), 2, RoundingMode.HALF_EVEN)).doubleValue() * 100);
                    }
                    result = compareCondition(persent, front);
                    break;

                default:
                    break;
            }
            if (result) {
                return result;
            }
            continue;
        }
        return result;
    }

    /**
     * 0:全部; 1:小于或等于; 2:等于; 3:大于或等于; 4:大于
     *
     * @param var
     * @param front
     * @return
     */
    private boolean compareCondition(Integer var, OverflowFront front) {
        boolean result = false;
        switch (front.getCompareCondition()) {
            case 0:
                return true;
            case 1:
                result = front.getRankValue().compareTo(var) >= 0;
                break;
            case 2:
                result = front.getRankValue().compareTo(var) == 0;
                break;
            case 3:
                result = front.getRankValue().compareTo(var) <= 0;
                break;
            case 4:
                result = front.getRankValue().compareTo(var) < 0;
                break;

            default:
                break;
        }
        return result;
    }


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

    public LineupStrategy getLineupStrategy() {
        return lineupStrategy;
    }

    public void setLineupStrategy(LineupStrategy lineupStrategy) {
        this.lineupStrategy = lineupStrategy;
    }
}
