package org.voice9.cc.websocket.response;


import com.voice9.core.enums.ErrorCode;

/**
 * Created by caoliang on 2020/11/3
 */
public class WsResponseEntity<T> {

    private int code;

    private String type;

    private String message = "success";

    private String agentKey;

    private T data;

    public WsResponseEntity(String type, String agentKey) {
        this.type = type;
        this.agentKey = agentKey;
    }

    public WsResponseEntity(int code, String message, String type, String agentKey) {
        this.code = code;
        this.message = message;
        this.type = type;
        this.agentKey = agentKey;
    }

    public WsResponseEntity(ErrorCode errorCode, String type, String agentKey, String... args) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        if (args != null && args.length > 0) {
            message = String.format(message, args);
        }
        this.type = type;
        this.agentKey = agentKey;
    }

    public WsResponseEntity(String type, String agentKey, T data) {
        this.type = type;
        this.agentKey = agentKey;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WsResponseEntity{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", agentKey='" + agentKey + '\'' +
                ", data=" + data +
                '}';
    }
}
