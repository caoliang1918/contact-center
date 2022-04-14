package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

import java.util.List;

/**
 * Create by caoliang on 2020/11/9
 * <p>
 * 班长监听
 */
public class WsMonitorEvent extends WsBaseEvent {

    private List<Long> groupIds;

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }
}
