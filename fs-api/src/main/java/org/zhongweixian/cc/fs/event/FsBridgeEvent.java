package org.zhongweixian.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.zhongweixian.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2020/10/14
 */
public class FsBridgeEvent extends FsBaseEvent {
    /**
     * 本地媒体端口
     */
    @JSONField(name = "variable_local_media_port")
    private Long mediaPort;

    /**
     * 远程媒体端口
     */
    @JSONField(name = "variable_remote_media_port")
    private Long remoteMediaPort;

    /**
     * 主叫号码(87)
     */
    @JSONField(name = "Caller-Destination-Number")
    private String callerIdName;

    /**
     * 工号(1002)
     */
    @JSONField(name = "variable_origination_caller_id_number")
    private String callerIdNumber;


    /**
     * 另外一侧号码 (被叫：13391723891)
     */
    @JSONField(name = "Other-Leg-Caller-ID-Name")
    private String otherCallerIdName;

    /**
     * 呼叫方向
     */
    @JSONField(name = "Caller-Logical-Direction")
    private String direction;


    /**
     * 主叫device
     */
    @JSONField(name = "Caller-Unique-ID")
    private String callerUniqueId;

    /**
     * 被叫device
     */
    @JSONField(name = "Other-Leg-Unique-ID")
    private String otherUniqueId;

    @JSONField(name = "variable_channel_name")
    private String channelName;

    @JSONField(name = "Other-Leg-Channel-Name")
    private String otherChannelName;

    public Long getMediaPort() {
        return mediaPort;
    }

    public void setMediaPort(Long mediaPort) {
        this.mediaPort = mediaPort;
    }

    public Long getRemoteMediaPort() {
        return remoteMediaPort;
    }

    public void setRemoteMediaPort(Long remoteMediaPort) {
        this.remoteMediaPort = remoteMediaPort;
    }

    public String getCallerIdName() {
        return callerIdName;
    }

    public void setCallerIdName(String callerIdName) {
        this.callerIdName = callerIdName;
    }

    public String getCallerIdNumber() {
        return callerIdNumber;
    }

    public void setCallerIdNumber(String callerIdNumber) {
        this.callerIdNumber = callerIdNumber;
    }

    public String getOtherCallerIdName() {
        return otherCallerIdName;
    }

    public void setOtherCallerIdName(String otherCallerIdName) {
        this.otherCallerIdName = otherCallerIdName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCallerUniqueId() {
        return callerUniqueId;
    }

    public void setCallerUniqueId(String callerUniqueId) {
        this.callerUniqueId = callerUniqueId;
    }

    public String getOtherUniqueId() {
        return otherUniqueId;
    }

    public void setOtherUniqueId(String otherUniqueId) {
        this.otherUniqueId = otherUniqueId;
    }

    @Override
    public String getChannelName() {
        return channelName;
    }

    @Override
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getOtherChannelName() {
        return otherChannelName;
    }

    public void setOtherChannelName(String otherChannelName) {
        this.otherChannelName = otherChannelName;
    }

    @Override
    public String toString() {
        return "ChannelBridgeEvent{" +
                "uuid='" + coreUuid + '\'' +
                ", mediaPort=" + mediaPort +
                ", remoteMediaPort=" + remoteMediaPort +
                ", callerIdName='" + callerIdName + '\'' +
                ", callerIdNumber='" + callerIdNumber + '\'' +
                ", otherCallerIdName='" + otherCallerIdName + '\'' +
                ", direction=" + direction +
                ", callerUniqueId='" + callerUniqueId + '\'' +
                ", otherUniqueId='" + otherUniqueId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", otherChannelName='" + otherChannelName + '\'' +
                ", eventName='" + eventName + '\'' +
                ", context=" + context +
                ", uuid='" + coreUuid + '\'' +
                ", timestamp=" + timestamp +
                ", channelName='" + channelName + '\'' +
                ", hostname='" + hostname + '\'' +
                ", map=" + map +
                '}';
    }
}
