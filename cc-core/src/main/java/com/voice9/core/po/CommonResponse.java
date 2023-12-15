package com.voice9.core.po;

import com.voice9.core.enums.ErrorCode;

/**
 * Created by caoliang on 2020/8/24
 */
public class CommonResponse<T> {
    private int code;
    private String message = "success";
    private T data;

    public CommonResponse() {
    }

    public CommonResponse(T data) {
        this.data = data;
    }

    public CommonResponse(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public CommonResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
