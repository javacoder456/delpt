package com.zh.business.exception;

public class BaseException extends RuntimeException{
    //异常信息
    private String message;

    public BaseException(String message){
        super(message);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
