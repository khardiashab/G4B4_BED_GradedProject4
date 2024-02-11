package com.learning.app.exceptionHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.app.exception.CustomException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(HttpServletRequest request,
            DataIntegrityViolationException ex) {
        // Check if the exception message contains information about the unique
        // constraint violation
        if (ex.getCause() instanceof ConstraintViolationException) {
            String errorMessage = "The value provided already exists. Please provide a unique value.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        // If the error cannot be handled specifically, provide a generic error message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while processing your request.");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(HttpServletRequest request,
            AuthenticationException ex) {
        // Handle authentication errors
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
        // Handle authorization errors
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationHandler(MethodArgumentNotValidException ex) {
        Set<String> errors = new HashSet<>();
        ex.getAllErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", HttpStatus.BAD_REQUEST);
        response.put("message", "Request Failed");
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> customExceptionHandler(CustomException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "Failure");
        response.put("message", ex.getLocalizedMessage());
        return new ResponseEntity<>(response, ex.getStatus());
    }

}
