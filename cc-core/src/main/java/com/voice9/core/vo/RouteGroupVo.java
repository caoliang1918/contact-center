package com.voice9.core.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by caoliang on 2021/5/26
 */
public class RouteGroupVo {


    private Long id;
    /**
     * 网关组
     */
    @NotBlank
    @Size(min = 2, max = 16, message = "网关组名称必须在2,16字符")
    private String routeGroup;

    @NotNull
    private List<Long> routeGetways;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteGroup() {
        return routeGroup;
    }

    public void setRouteGroup(String routeGroup) {
        this.routeGroup = routeGroup;
    }

    public List<Long> getRouteGetways() {
        return routeGetways;
    }

    public void setRouteGetways(List<Long> routeGetways) {
        this.routeGetways = routeGetways;
    }
}
