package com.learning.app.exception;

public class CustomException extends RuntimeException {

    private final int errorCode;

    public CustomException(String arg0, int errorCode) {
        super(arg0);
        this.errorCode = errorCode;
    }

    public CustomException(String arg0, Throwable arg1, int errorCode) {
        super(arg0, arg1);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
