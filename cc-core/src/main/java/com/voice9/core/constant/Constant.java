package com.voice9.core.constant;

/**
 * Created by caoliang on 2020/10/21
 */
public class Constant {

    public static final String UTF_8 = "UTF-8";
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
    public static final String JSON = ".json";
    public static final String XML = ".xml";



    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";

    public static final String RRCORD_BUCKET = "cc-record";
    public static final String VOICE_BUCKET = "cc-voice";
    public static final String IVR_BUCKET = "cc-ivr";

    public final static String SORT = "sort";
    public final static String ID_DESC = "id desc";
    public final static String ID_ASC = "id asc";

    public final static String PAGE_NUM = "pageNum";
    public final static String PAGE_SIZE = "pageSize";

    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String UNKNOWN = "unknown";



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


    public final static String AGENT_TOKEN = "AGENT_TOKEN:";
    public final static String AGENT_INFO = "AGENT_INFO:";

    public final static String ADMIN_TOKEN = "ADMIN_TOKEN:";
    public final static String ADMIN_INFO = "ADMIN_INFO:";


    public final static String CALL_INFO = "CALL_INFO:";
    public static String FS_API = "fs-api";
}
