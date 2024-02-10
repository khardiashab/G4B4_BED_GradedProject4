package com.learning.app.exception;

import org.springframework.http.HttpStatus;

public class NotAValidRoleExcetpion extends CustomException {

    public NotAValidRoleExcetpion(String message) {
        super("Not a valid type Role : " + message, HttpStatus.UNPROCESSABLE_ENTITY.value());
    }
    
    public NotAValidRoleExcetpion(int roleId) {
        super("Role not Exist with id: " + roleId, HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

}