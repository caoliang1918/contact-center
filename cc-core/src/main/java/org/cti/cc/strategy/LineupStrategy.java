package org.cti.cc.strategy;

import org.cti.cc.po.CallInfo;

/**
 * Created by caoliang on 2021/8/2
 * <p>
 * 多媒体在技能组中排队策略
 */
public interface LineupStrategy {

    /**
     * 计算电话多媒体进技能组
     *
     * @param callInfo
     * @return
     */
    Long calculateLevel(CallInfo callInfo);
}
