package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 日程表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class VdnSchedule implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     * 创建时间
     */
    private Long cts;

    /**
     * 修改时间
     */
    private Long uts;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 日程名称
     */
    private String name;

    /**
     * 优先级
     */
    private Integer levelValue;

    /**
     * 1:指定时间,2:相对时间
     */
    private Integer type;

    /**
     * 开始日期
     */
    private String startDay;

    /**
     * 结束日期
     */
    private String endDay;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 周一
     */
    private Integer mon;

    /**
     * 周二
     */
    private Integer tue;

    /**
     * 周三
     */
    private Integer wed;

    /**
     * 周四
     */
    private Integer thu;

    /**
     * 周五
     */
    private Integer fri;

    /**
     * 周六
     */
    private Integer sat;

    /**
     * 周天
     */
    private Integer sun;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCts() {
        return cts;
    }

    public void setCts(Long cts) {
        this.cts = cts;
    }

    public Long getUts() {
        return uts;
    }

    public void setUts(Long uts) {
        this.uts = uts;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay == null ? null : startDay.trim();
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay == null ? null : endDay.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public Integer getMon() {
        return mon;
    }

    public void setMon(Integer mon) {
        this.mon = mon;
    }

    public Integer getTue() {
        return tue;
    }

    public void setTue(Integer tue) {
        this.tue = tue;
    }

    public Integer getWed() {
        return wed;
    }

    public void setWed(Integer wed) {
        this.wed = wed;
    }

    public Integer getThu() {
        return thu;
    }

    public void setThu(Integer thu) {
        this.thu = thu;
    }

    public Integer getFri() {
        return fri;
    }

    public void setFri(Integer fri) {
        this.fri = fri;
    }

    public Integer getSat() {
        return sat;
    }

    public void setSat(Integer sat) {
        this.sat = sat;
    }

    public Integer getSun() {
        return sun;
    }

    public void setSun(Integer sun) {
        this.sun = sun;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cts=").append(cts);
        sb.append(", uts=").append(uts);
        sb.append(", companyId=").append(companyId);
        sb.append(", name=").append(name);
        sb.append(", levelValue=").append(levelValue);
        sb.append(", type=").append(type);
        sb.append(", startDay=").append(startDay);
        sb.append(", endDay=").append(endDay);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", mon=").append(mon);
        sb.append(", tue=").append(tue);
        sb.append(", wed=").append(wed);
        sb.append(", thu=").append(thu);
        sb.append(", fri=").append(fri);
        sb.append(", sat=").append(sat);
        sb.append(", sun=").append(sun);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VdnSchedule other = (VdnSchedule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLevelValue() == null ? other.getLevelValue() == null : this.getLevelValue().equals(other.getLevelValue()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStartDay() == null ? other.getStartDay() == null : this.getStartDay().equals(other.getStartDay()))
            && (this.getEndDay() == null ? other.getEndDay() == null : this.getEndDay().equals(other.getEndDay()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getMon() == null ? other.getMon() == null : this.getMon().equals(other.getMon()))
            && (this.getTue() == null ? other.getTue() == null : this.getTue().equals(other.getTue()))
            && (this.getWed() == null ? other.getWed() == null : this.getWed().equals(other.getWed()))
            && (this.getThu() == null ? other.getThu() == null : this.getThu().equals(other.getThu()))
            && (this.getFri() == null ? other.getFri() == null : this.getFri().equals(other.getFri()))
            && (this.getSat() == null ? other.getSat() == null : this.getSat().equals(other.getSat()))
            && (this.getSun() == null ? other.getSun() == null : this.getSun().equals(other.getSun()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLevelValue() == null) ? 0 : getLevelValue().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStartDay() == null) ? 0 : getStartDay().hashCode());
        result = prime * result + ((getEndDay() == null) ? 0 : getEndDay().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getMon() == null) ? 0 : getMon().hashCode());
        result = prime * result + ((getTue() == null) ? 0 : getTue().hashCode());
        result = prime * result + ((getWed() == null) ? 0 : getWed().hashCode());
        result = prime * result + ((getThu() == null) ? 0 : getThu().hashCode());
        result = prime * result + ((getFri() == null) ? 0 : getFri().hashCode());
        result = prime * result + ((getSat() == null) ? 0 : getSat().hashCode());
        result = prime * result + ((getSun() == null) ? 0 : getSun().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}