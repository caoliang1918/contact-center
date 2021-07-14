package org.cti.cc.po;

import org.cti.cc.entity.RouteCall;
import org.cti.cc.entity.RouteGroup;

/**
 * Created by caoliang on 2020/11/8
 */
public class RouteCallInfo extends RouteCall {

    /**
     * 网关组
     */
    private RouteGroupPo routeGroupPo;

    public RouteGroupPo getRouteGroupPo() {
        return routeGroupPo;
    }

    public void setRouteGroupPo(RouteGroupPo routeGroupPo) {
        this.routeGroupPo = routeGroupPo;
    }
}
