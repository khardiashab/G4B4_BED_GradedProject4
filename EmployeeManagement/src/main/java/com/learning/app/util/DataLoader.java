package com.learning.app.util;

import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.github.javafaker.Faker;
import com.learning.app.entity.Employee;
import com.learning.app.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private Faker faker = new Faker();
    private final EmployeeService employeeService;

    @EventListener(ApplicationReadyEvent.class)
    public void addEmployees(ApplicationReadyEvent event) {
        IntStream.range(0, 100).forEach(index -> {
            getNewEmployee();
        });
    }

    private void getNewEmployee() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = (firstName + lastName).replaceAll(" ", "").trim().toLowerCase() + "@greatlearning.com";
        Employee newEmployee = new Employee(firstName, lastName, email);
        employeeService.save(newEmployee);
    }

}