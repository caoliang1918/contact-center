package org.zhongweixian.api.vo.server;

import javax.validation.constraints.NotNull;

/**
 * Created by caoliang on 2022/4/6
 */
public class RoleVo {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
