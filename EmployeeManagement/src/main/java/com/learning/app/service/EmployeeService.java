package com.learning.app.service;

import java.util.List;

import com.learning.app.entity.Employee;

public interface EmployeeService {

    Employee save(Employee employee);

    String deleteById(Long employeeId);

    Employee findById(Long employeeId);

    List<Employee> findAll(String sortOrder);

    List<Employee> findAll();

    List<Employee> findByFirstName(String firstName);

    Employee update(Long employeeId, Employee employee);

    List<Employee> saveAllAndFlush(List<Employee> employees);

}