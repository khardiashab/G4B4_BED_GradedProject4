package com.learning.app.exceptionHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.app.exception.CustomException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationHandler(MethodArgumentNotValidException ex) {
        Set<String> errors = new HashSet<>();
        ex.getAllErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", HttpStatus.UNPROCESSABLE_ENTITY);
        response.put("message", "Request Failed");
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> customExceptionHandler(CustomException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", ex.getErrorCode());
        response.put("message", ex.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode()));
    }
}
