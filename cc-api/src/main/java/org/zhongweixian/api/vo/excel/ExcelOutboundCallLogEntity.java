package org.zhongweixian.api.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.cti.cc.entity.CallLog;

/**
 * Created by caoliang on 2021/9/21
 */
public class ExcelOutboundCallLogEntity extends CallLog {


    /**
     * 企业id
     */
    @Excel(name = "企业id", width = 8)
    private Long companyId;

    /**
     * 话单id
     */
    @Excel(name = "话单id", width = 22)
    private Long callId;

    /**
     * 主叫显号
     */
    @Excel(name = "主叫显号", width = 15)
    private String callerDisplay;

    /**
     * 主叫
     */
    @Excel(name = "主叫", width = 15)
    private String caller;

    /**
     * 被叫显号
     */
    @Excel(name = "被叫显号", width = 15)
    private String calledDisplay;

    /**
     * 被叫
     */
    @Excel(name = "被叫", width = 15)
    private String called;

    /**
     * 坐席
     */
    @Excel(name = "坐席", width = 15)
    private String agentKey;

    /**
     * 技能组
     */
    @Excel(name = "技能组", width = 10)
    private Long groupId;

    /**
     * 1:sip号,2:webrtc,3:手机
     */
    @Excel(name = "sip", width = 10, replace = {"sip_1", "webrtc_2", "手机_3"})
    private Integer loginType;

    /**
     * 任务ID
     */
    @Excel(name = "任务id", width = 10)
    private Long taskId;

    /**
     * ivr
     */
    @Excel(name = "ivr", width = 10)
    private Long ivrId;

    /**
     * 机器人id
     */
    @Excel(name = "机器人", width = 10)
    private Long botId;

    /**
     * 呼叫开始时间
     */
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd")
    private Long callTime;

    /**
     * 接听时间
     */
    @Excel(name = "振铃时间", width = 20, format = "yyyy-MM-dd")
    private Long answerTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", width = 20, format = "yyyy-MM-dd")
    private Long endTime;

    /**
     * 呼叫类型
     */
    @Excel(name = "呼叫类型", width = 20)
    private String callType;

    /**
     * 呼叫方向
     */
    @Excel(name = "呼叫方向", width = 10, replace = {"外呼_OUTBOUND", "呼入_INBOUND"})
    private String direction;

    /**
     * 通话标识(0:接通,1:坐席未接用户未接,2:坐席接通用户未接通,3:用户接通坐席未接通)
     */
    @Excel(name = "通话标识", width = 15, replace = {"接通_0", "坐席未接通_1", "用户未接通_2", "坐席未接通_3"})
    private Integer answerFlag;

    /**
     * 应答设备数
     */
    @Excel(name = "应答数", width = 10)
    private Integer answerCount;

    /**
     * 挂机方向(1:主叫挂机,2:被叫挂机,3:系统挂机)
     */
    @Excel(name = "挂机方向", width = 12, replace = {"主叫挂机_1", "被叫挂机_2", "系统挂机_3"})
    private Integer hangupDir;

    /**
     * 挂机原因
     */
    @Excel(name = "挂机原因", width = 25)
    private String hangupCause;

    /**
     * 录音地址
     */
    @Excel(name = "录音地址", width = 15)
    private String record;

    /**
     * 录音开始时间
     */
    @Excel(name = "录音开始时间", width = 20, format = "yyyy-MM-dd")
    private Long recordTime;

    /**
     * 通话时长(秒)
     */
    @Excel(name = "通话时长", width = 10)
    private Long talkTime;

    /**
     * 通话随路数据(2048)
     */
    @Excel(name = "随路数据", width = 15)
    private String followData;

    /**
     * 扩展1
     */
    @Excel(name = "uuid1", width = 15)
    private String uuid1;

    /**
     * 扩展2
     */
    @Excel(name = "uuid2", width = 15)
    private String uuid2;


}
