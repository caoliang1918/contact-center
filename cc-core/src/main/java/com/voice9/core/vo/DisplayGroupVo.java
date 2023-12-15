package com.voice9.core.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DisplayGroupVo {


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
     * 企业id
     */
    private Long companyId;

    /**
     * 号码池
     */
    @NotBlank(message = "号码池名称不能为空")
    @Size(min = 4, max = 16, message = "号码池名称长度必须在4-16位")
    private String name;

    /**
     * 1:呼入号码,2:主叫显号,3:被叫显号
     */
    @NotNull(message = "号码池类型不能为空")
    @Range(min = 1, max = 3, message = "号码池类型错误")
    private Integer type;


    @NotNull(message = "号码池号码不能为空")
    private List<Long> phoneIds;

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
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Long> getPhoneIds() {
        return phoneIds;
    }

    public void setPhoneIds(List<Long> phoneIds) {
        this.phoneIds = phoneIds;
    }
}
