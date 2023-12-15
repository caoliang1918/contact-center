package com.voice9.core.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by caoliang on 2021/11/1
 */
public class BatchAddAgentVo {

    /**
     * 添加坐席数
     */
    @NotNull(message = "坐席数量不能为空")
    @Range(min = 1, max = 1000)
    private Integer count;

    /**
     * 坐席前缀
     */
    @Size(max = 4)
    private String prefix;

    /**
     * 起始坐席编号
     */
    @NotNull
    @Range(min = 100 , max = 1000000)
    private Integer start;


    /**
     * 座席密码
     */
    @NotNull(message = "坐席密码不能为空")
    @Size(min = 8, max = 32, message = "密码长度不对")
    private String passwd;

    /**
     * 企业id
     */
    private Long companyId;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
