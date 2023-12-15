package com.voice9.api.exception;

import com.voice9.core.enums.ErrorCode;

/**
 * @author caoliang
 */
public class BusinessException  extends RuntimeException{

    private Integer errorCode;

    private String message;

    public BusinessException(ErrorCode errorCode){
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
    public BusinessException(ErrorCode errorCode , String message){
        this.errorCode = errorCode.getCode();
        this.message = message;
    }

    public BusinessException(Integer errorCode , String  message){
        this.errorCode = errorCode;
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
