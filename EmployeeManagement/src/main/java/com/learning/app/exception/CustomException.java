package com.learning.app.exception;

public class CustomException extends RuntimeException {

    private final int errorCode;

    public CustomException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(String message, Throwable arg1, int errorCode) {
        super(message, arg1);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
