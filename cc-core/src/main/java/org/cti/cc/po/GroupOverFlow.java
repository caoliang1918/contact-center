package org.cti.cc.po;

import org.cti.cc.entity.OverflowConfig;
import org.cti.cc.entity.OverflowExp;
import org.cti.cc.entity.OverflowFront;

import java.util.List;

/**
 * Created by caoliang on 2020/06/06
 */
public class GroupOverFlow extends OverflowConfig {


    /**
     * 前置条件
     */
    private List<OverflowFront> overflowFronts;


    /**
     * 自定义策略
     */
    private List<OverflowExp> overflowExps;

    public List<OverflowFront> getOverflowFronts() {
        return overflowFronts;
    }

    public void setOverflowFronts(List<OverflowFront> overflowFronts) {
        this.overflowFronts = overflowFronts;
    }

    public List<OverflowExp> getOverflowExps() {
        return overflowExps;
    }

    public void setOverflowExps(List<OverflowExp> overflowExps) {
        this.overflowExps = overflowExps;
    }
}
