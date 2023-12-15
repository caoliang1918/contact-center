package com.voice9.core.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by caoliang on 2021/5/24
 */
public class RouteCallVo {

    private Long id;

    /**
     * 企业ID
     */
    private Long companyId;
    /**
     * 所属路由组
     */
    @NotNull
    private Long routeGroupId;

    /**
     * 字冠号码
     */
    @NotBlank
    @Size(min = 1,max = 32,message = "字冠号码必须在1,32个字符")
    private String routeNum;

    /**
     * 最长
     */
    @NotNull
    @Range(min = 1 , max = 32 , message = "字冠匹配最长长度在1,32")
    private Integer numMax;

    /**
     * 最短
     */
    @NotNull
    @Range(min = 1 , max = 32 , message = "字冠匹配最短长度在1,32")
    private Integer numMin;

    /**
     * 主叫替换规则
     */
    private Integer callerChange;

    /**
     * 替换号码
     */
    private String callerChangeNum;

    /**
     * 被叫替换规则
     */
    private Integer calledChange;

    /**
     * 替换号码
     */
    private String calledChangeNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(Long routeGroupId) {
        this.routeGroupId = routeGroupId;
    }

    public String getRouteNum() {
        return routeNum;
    }

    public void setRouteNum(String routeNum) {
        this.routeNum = routeNum;
    }

    public Integer getNumMax() {
        return numMax;
    }

    public void setNumMax(Integer numMax) {
        this.numMax = numMax;
    }

    public Integer getNumMin() {
        return numMin;
    }

    public void setNumMin(Integer numMin) {
        this.numMin = numMin;
    }

    public Integer getCallerChange() {
        return callerChange;
    }

    public void setCallerChange(Integer callerChange) {
        this.callerChange = callerChange;
    }

    public String getCallerChangeNum() {
        return callerChangeNum;
    }

    public void setCallerChangeNum(String callerChangeNum) {
        this.callerChangeNum = callerChangeNum;
    }

    public Integer getCalledChange() {
        return calledChange;
    }

    public void setCalledChange(Integer calledChange) {
        this.calledChange = calledChange;
    }

    public String getCalledChangeNum() {
        return calledChangeNum;
    }

    public void setCalledChangeNum(String calledChangeNum) {
        this.calledChangeNum = calledChangeNum;
    }
}
