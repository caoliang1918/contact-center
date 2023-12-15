package org.voice9.cc.websocket.event;

import org.voice9.cc.websocket.event.base.WsBaseEvent;

import java.util.Map;

/**
 * Created by caoliang on 2021/7/19
 */
public class WsUpdateFollowDataEvent extends WsBaseEvent {

    /**
     * 随路数据
     */
    private Map<String, Object> followData;

    public Map<String, Object> getFollowData() {
        return followData;
    }

    public void setFollowData(Map<String, Object> followData) {
        this.followData = followData;
    }
}
