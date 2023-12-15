package com.voice9.core.enums;

/**
 * Create by caoliang on 2020/06/06
 */
public enum ErrorCode {
    RUNTIME_EXCEPTION(1001, "未知错误"),
    NULL_POINT_EXCEPTION(1002, "空指针异常"),
    DB_EXCEPTION(1003, "数据库异常"),
    DUPLICATE_EXCEPTION(1004, "唯一约束异常"),
    METHOD_NOT_SUPPORT_EXCEPTION(1005, "不支持的请求方法"),
    DATA_NOT_EXIST(1006, "数据不存在"),
    PARAMETER_ERROR(1010, "%s参数异常"),
    DATA_IS_USED(1011, "数据被引用，请先删除引用数据"),
    RUNTIME_DATA_EXCEPTION(1050, "%s"),

    MEDIA_NOT_AVALIABLE(1100, "媒体不可用"),
    COMPANY_NOT_AVALIABLE(1101, "企业不可用"),
    ACCOUNT_ERROR(1102, "账号或密码错误"),
    ACCOUNT_DISABLED(1103, "账号被禁用"),
    ACCOUNT_AUTH_ERROR(1104, "账号权限错误"),
    ACCOUNT_NOT_LOGIN(1105, "账号未登录"),
    FILE_NOT_EXIST(1106, "文件不存在"),


    ACCOUNT_SIP_NOTNUL(1201, "坐席sip号不能为空"),
    ACCOUNT_PHONE_NOTNULL(1202, "坐席手机号不能为空"),
    AGENT_NAME_LENGTH_ERROR(1203, "坐席工号必须在4,10字符"),
    AGENT_OVER_LIMIT(1204, "企业坐席超过限制"),
    GROUP_OVER_LIMIT(1205, "企业技能组超过限制"),
    GROUP_AGENT_OVER_LIMIT(1206, "技能组中坐席超过限制"),
    GROUP_SKILL_OVER_LIMIT(1207, "技能组中技能超过限制"),
    AGENT_SKILL_OVER_LIMIT(1208, "坐席技能不能超过20个"),
    CALL_LOG_EXPORT_LIMIT(1209, "话单导出不能超时{0}"),
    EXPORT_EMPTY(1210, "导出数据为空"),


    AGENT_GROUP_NULL(2001, "技能组为空"),
    AGENT_CALLING(2002, "坐席通话中"),
    AGENT_LOGIN_IN_OTHER(2003, "坐席在别处登录"),
    AGENT_REPEAT_LOGIN(2004, "坐席重复登录"),
    AGENT_ALREADY_NOT_READY(2005, "坐席已经忙碌"),
    AGENT_ALREADY_READY(2006, "坐席已经空闲"),
    CALL_NOT_EXIST(2007, "电话不存在"),
    AGENT_NOT_ONLINE(2008, "坐席不在线"),
    //不处于READY NOT_READY
    AGENT_BUSY(2009, "坐席忙碌"),
    CALL_ANSWERED(2010, "电话已经应答"),


    CALL_NUMBER_ERROR(3000, "号码错误"),
    CALLTYPE_ERROR(3001, "呼叫类型错误"),
    CALL_ROUTE_ERROR(3002, "路由错误"),
    CALLER_DISPLAY_ERROR(3003, "主叫显号错误"),
    CALLED_DISPLAY_ERROR(3004, "被叫显号错误"),
    CONSULT_ERROR(3004, "被叫显号错误"),
    CALL_RUNTIME_ERROR(3500, "呼叫中异常");


    private Integer code;

    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
