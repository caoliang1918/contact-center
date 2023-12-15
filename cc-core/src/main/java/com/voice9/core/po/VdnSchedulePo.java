package com.voice9.core.po;

import com.voice9.core.entity.VdnConfig;
import com.voice9.core.entity.VdnDtmf;
import com.voice9.core.entity.VdnSchedule;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Create by caoliang on 2020/11/29
 */
public class VdnSchedulePo extends VdnConfig implements Comparable<VdnSchedulePo> {

    /**
     * 每个字码日程有多个按键导航
     */
    private List<VdnDtmf> dtmfList;

    /**
     * 日程
     */
    private VdnSchedule scheduleConfig;


    public List<VdnDtmf> getDtmfList() {
        return dtmfList;
    }

    public void setDtmfList(List<VdnDtmf> dtmfList) {
        this.dtmfList = dtmfList;
    }

    public VdnSchedule getScheduleConfig() {
        return scheduleConfig;
    }

    public void setScheduleConfig(VdnSchedule scheduleConfig) {
        this.scheduleConfig = scheduleConfig;
    }

    @Override
    public int compareTo(VdnSchedulePo o) {
        return this.getScheduleConfig().getLevelValue() - o.getScheduleConfig().getLevelValue();
    }

    public boolean isEffectiveSchedule() {
        if (scheduleConfig == null) {
            return false;
        }
        LocalDate localDate = LocalDate.now();
        int week = localDate.getDayOfMonth();

        switch (week) {
            case 1:
                if (this.scheduleConfig.getMon() == 0) {
                    return false;
                }
                break;
            case 2:
                if (this.scheduleConfig.getTue() == 0) {
                    return false;
                }
                break;
            case 3:
                if (this.scheduleConfig.getWed() == 0) {
                    return false;
                }
                break;
            case 4:
                if (this.scheduleConfig.getThu() == 0) {
                    return false;
                }
                break;
            case 5:
                if (this.scheduleConfig.getFri() == 0) {
                    return false;
                }
                break;
            case 6:
                if (this.scheduleConfig.getSat() == 0) {
                    return false;
                }
                break;
            case 7:
                if (this.scheduleConfig.getSun() == 0) {
                    return false;
                }
                break;

            default:
                break;
        }


        //判断是否在生效日期内
        int dayOfMonth = localDate.getDayOfMonth();
        if (StringUtils.isNotBlank(scheduleConfig.getStartDay())) {
            LocalDate startDate = LocalDate.parse(scheduleConfig.getStartDay(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (scheduleConfig.getType() == 1) {
                //相对时间
                if (startDate.getDayOfMonth() > dayOfMonth) {
                    return false;
                }
            } else if (startDate.isAfter(localDate)) {
                return false;
            }
        }
        if (StringUtils.isNotBlank(scheduleConfig.getEndDay())) {
            final LocalDate endDate = LocalDate.parse(scheduleConfig.getEndDay(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (scheduleConfig.getType() == 1) {
                //相对时间
                if (endDate.getDayOfMonth() < dayOfMonth) {
                    return false;
                }
            } else if (endDate.isBefore(localDate)) {
                return false;
            }
        }

        //判断是否在生效的时间范围内（当天）
        LocalTime nowTime = LocalTime.now();
        if (StringUtils.isNotBlank(scheduleConfig.getStartTime())) {
            LocalTime startTime = LocalTime.parse(scheduleConfig.getStartTime(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            if (startTime.isAfter(nowTime)) {
                return false;
            }
        }
        if (StringUtils.isNotBlank(scheduleConfig.getEndTime())) {
            LocalTime endTime = LocalTime.parse(scheduleConfig.getEndTime(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            if (endTime.isBefore(nowTime)) {
                return false;
            }
        }
        return true;
    }
}
