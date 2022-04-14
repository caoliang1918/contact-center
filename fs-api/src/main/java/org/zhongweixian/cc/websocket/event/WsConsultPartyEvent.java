package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Created by caoliang on 2022/2/8.
 * <p>
 * 咨询多方：
 * 先发起咨询，被咨询的坐席需要一个一个拉进会议
 */
public class WsConsultPartyEvent extends WsBaseEvent {

    /**
     * 会议号
     */
    private String conference;


    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

}
