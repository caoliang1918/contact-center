package org.cti.cc.constant;

/**
 * Created by caoliang on 2020/10/21
 */
public class Constants {

    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String LF = "\n";
    public static final String CR = "\r";
    public static final String AT = "@";
    public static final String CO = ":";
    public static final String UNDER_LINE = "_";
    public static final String POINT = ".";
    public static final String SK = "/";
    public static final String LINE = "-";


    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";

    /**
     * vip等级进线
     */
    public static final String VIP_LEVEL = "vipLevel";

    public static final String START_TIME = "startTime";
    public static final String QUEUE_TIME = "queueTime";

    /**
     * 指定坐席
     */
    public static final String DESIGNATE_AGENT = "desiganteAgent";

    /**
     * 当前空闲时间
     */
    public static final String LONG_READY = "longReady";

    /**
     * 空闲次数
     */
    public static final String READY_TIMES = "readyTimes";

    /**
     * 服务次数
     */
    public static final String SEREVICE_TIMES = "serviceTimes";


    public final static String DEFAULT_KEY = "";

    /**
     * 坐席状态相关
     */
    public final static String AGENT_STATE_KEY = "STATE";
    public final static String AGENT_LOG_KEY = "LOG";
    public final static String DEVOCE_KEY = "DEVICE";
    public final static String DETAIL_KEY = "DETAIL";
    public final static String CALLLOG_KEY = "CALLLOG";

    public final static String AGENT_STATE_EXCHANGE = "AGENT-STATE-EXCHANGE";
    public final static String CALL_LOG_EXCHANGE = "CALL-LOG-EXCHANGE";

    /**
     * 话单相关
     */
    public final static String CALL_DEVICE_QUEUE = "CALL-DEVICE-QUEUE";
    public final static String CALL_LOG_QUEUE = "CALL-LOG-QUEUE";
    public final static String CALL_DETAIL_QUEUE = "CALL-DETAIL-QUEUE";


    public final static String AGENT_STATE_QUEUE = "AGENT-STATE-QUEUE";
    public final static String AGENT_LOG_QUEUE = "AGENT-LOG-QUEUE";


    /**
     * 配置变更相关
     */
    public final static String CC_CONFIG_EXCHANGE = "CC-CONFIG-EXCHANGE";
    public final static String CC_CONFIG_QUEUE = "CC-CONFIG-QUEUE";


}
