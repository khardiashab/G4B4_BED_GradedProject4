package com.learning.app.exception;

public class InvalidUserException extends CustomException {

    public InvalidUserException(String arg0, int errorCode) {
        super(arg0, errorCode);
    }
    
}
