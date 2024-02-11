package com.learning.app.exception;

import org.springframework.http.HttpStatus;

public class EmployeeNotFounException extends CustomException {

    public EmployeeNotFounException(long employeeId) {
        super("Employee is not found with id: " + employeeId, HttpStatus.NOT_FOUND);
    }

    public EmployeeNotFounException(String employeeFirstName) {
        super("Employee is not found with FirstName: " + employeeFirstName, HttpStatus.NOT_FOUND);
    }

}
