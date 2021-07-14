package org.zhongweixian.cc;

import org.zhongweixian.cc.tcp.event.SubMakeCallEvent;
import org.zhongweixian.cc.tcp.event.SubStopCallEvent;
import org.zhongweixian.cc.websocket.event.*;
import org.zhongweixian.cc.fs.event.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoliang on 2020/8/29
 */
public enum EventType {

    /**
     *
     */
    CHANNEL_OUTGOING(1001, FsOutgoingEvent.class),

    /**
     *
     */
    CHANNEL_PARK(1002, FsParkEvent.class),

    /**
     *
     */
    CHANNEL_ANSWER(1005, FsAnswerEvent.class),

    /**
     *
     */
    CHANNEL_EXECUTE(1006, FsExecuteEvent.class),

    /**
     *
     */
    CHANNEL_EXECUTE_COMPLETE(1007, FsExecuteComplateEvent.class),

    /**
     * 开始放音
     */
    PLAYBACK_START(1008, FsPlaybackStart.class),

    /**
     * 放音结束
     */
    PLAYBACK_STOP(1009, FsPlaybackStop.class),


    /**
     *
     */
    CHANNEL_BRIDGE(1010, FsBridgeEvent.class),

    /**
     *
     */
    CHANNEL_HANGUP(1011, FsHangupEvent.class),

    /**
     *
     */
    DTMF(1012, FsDtmfEvent.class),

    /**
     * 媒体识别回铃音
     */
    RING_ASR(1013, FsRingAsrEvent.class),


    /***************************************************/

    /**
     * 坐席登录
     */
    WS_LOGIN(2001, WsLoginEvnet.class),

    /**
     * 登录之后的上班状态
     */
    WS_WORK_READY(2002, WsWorkReadyEvent.class),

    /**
     * 自定义忙碌状态
     */
    WS_WORK_NOT_READY(2003, WsWorkNotReadyEvent.class),

    /**
     * 空闲
     */
    WS_READY(2004, WsReadyEvent.class),

    /**
     * 忙碌
     */
    WS_NOT_READY(2005, WsNotReadyEvent.class),

    /**
     * 手动外呼
     */
    WS_MAKE_CALL(2006, WsMakeCallEvent.class),

    /**
     * 取消拨打
     */
    WS_CANCEL_MAKECALL(2007, WsCancelMakeCallEvent.class),

    /**
     * 接起
     */
    WS_ANSWER(2008, WsAnswerEvent.class),

    /**
     * 呼叫保持
     */
    WS_HOLD(2009, WsHoldEvent.class),

    /**
     * 取消保持
     */
    WS_CANCEL_HOLD(2010, WsCancelHoldEvent.class),

    /**
     * 挂机
     */
    WS_HANGUP_CALL(2011, WsHangupCallEvent.class),

    /**
     * 通话中更新随路数据
     */
    WS_TALKING_FOLLOW_DATA(2020, WsMakeCallEvent.class),

    /**
     * 坐席发送dtmf
     */
    WS_DTMF(2021, WsDtmfEvent.class),

    /**
     * 咨询
     */
    WS_COONSULT(2030, WsConsultEvent.class),

    /**
     * 取消咨询
     */
    WS_COONSULT_CANCEL(2031, WsConsultCancelEvent.class),


    /**
     * 转接(包括转接坐席，技能组，IVR)
     */
    WS_TRANSFER(2033, WsTransferEvent.class),

    /**
     * 取消转接
     */
    WS_TRANSFER_CANCEL(2034, WsTransferCancelEvent.class),

    /**
     * 加入会议
     */
    WS_ROOM(2035, WsJoinRoomEvent.class),

    /**
     * 班长监听
     */
    WS_LISTEN(2036, WsListenEvent.class),

    /**
     * 班长强插
     */
    WS_FORCE_CALL(2037, WsForceCallEvent.class),

    /**
     * 班长强拆
     */
    WS_BREAK(2038, WsBreakEvent.class),

    /**
     * 强制空闲
     */
    WS_FORCE_READY(2039, WsForceReadyEvent.class),
    /**
     * 强制忙碌
     */
    WS_FORCE_NOT_READY(2040, WsForceNotReadyEvent.class),

    /**
     * 强制退出
     */
    WS_FORCE_LOGOUT(2041, WsForceLogoutEvent.class),

    /**
     * 坐席监控
     */
    WS_MONITOR(2042, WsMonitorEvent.class),

    /**
     * 取消坐席监控
     */
    WS_MONITOR_CANCEL(2043, WsMonitorCancelEvent.class),
    /**
     * 坐席退出系统
     */
    WS_LOGOUT(2050, WsLogoutEvent.class),


    /***************************************************/

    SUB_MAKE_CALL(3001, SubMakeCallEvent.class),

    SUB_STOP_CALL(3990, SubStopCallEvent.class);


    private Integer index;

    private Class eventClass;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Class getEventClass() {
        return eventClass;
    }

    public void setEventClass(Class eventClass) {
        this.eventClass = eventClass;
    }

    EventType(Integer index, Class eventClass) {
        this.index = index;
        this.eventClass = eventClass;
    }


    static Map<String, Class> classMap = new HashMap<>();

    static {
        for (EventType value : EventType.values()) {
            classMap.put(value.name(), value.eventClass);
        }
    }

    public static Class getClassByCmd(String cmd) {
        return classMap.get(cmd);
    }
}
