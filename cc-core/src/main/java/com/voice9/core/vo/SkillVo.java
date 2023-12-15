package com.voice9.core.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by caoliang on 2021/12/8
 */
public class SkillVo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 企业id
     */
    private Long companyId;

    @NotBlank(message = "技能名称不能为空")
    @Size(min = 2, max = 16, message = "技能名称在2-16位")
    private String name;

    /**
     *
     */
    @Size(min = 1, max = 100, message = "技能备注在1-100位")
    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
