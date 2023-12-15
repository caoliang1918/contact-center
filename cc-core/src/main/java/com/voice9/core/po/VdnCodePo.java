package com.voice9.core.po;

import com.voice9.core.entity.VdnCode;

import java.util.List;

/**
 * Create by caoliang on 2020/11/29
 */
public class VdnCodePo extends VdnCode {

    /**
     * 每个vdn有多个日程-字码
     */
    private List<VdnSchedulePo> vdnSchedulePoList;


    public List<VdnSchedulePo> getVdnSchedulePoList() {
        return vdnSchedulePoList;
    }

    public void setVdnSchedulePoList(List<VdnSchedulePo> vdnSchedulePoList) {
        this.vdnSchedulePoList = vdnSchedulePoList;
    }


    /**
     * 获取有效日程
     *
     * @return
     */
    public VdnSchedulePo getEffectiveSchedule() {
        if (this.vdnSchedulePoList.size() == 0) {
            return null;
        }
        // vdnSchedulePoList已经按照日程的优先级排序了
        for (VdnSchedulePo vdnSchedulePo : vdnSchedulePoList) {
            if (vdnSchedulePo.isEffectiveSchedule()) {
                return vdnSchedulePo;
            }
        }
        return null;
    }
}
