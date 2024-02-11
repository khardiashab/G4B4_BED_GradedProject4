package com.learning.app.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learning.app.entity.Employee;
import com.learning.app.exception.EmployeeNotFounException;
import com.learning.app.repository.EmployeeRepository;
import com.learning.app.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public String deleteById(Long employeeId) {
        repository.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }

    @Override
    public Employee findById(Long employeeId) {
        return repository.findById(employeeId).orElseThrow(() -> new EmployeeNotFounException(employeeId));
    }

    @Override
    public List<Employee> findAll(String sortOrder) {
        Sort.Order order;
        // Remove non-alphabetic characters from the beginning and end of the string
        sortOrder = sortOrder.replaceAll("^[^a-zA-Z]+|[^a-zA-Z]+$", "");
        if (sortOrder.equalsIgnoreCase("desc")) {
            order = Sort.Order.desc("firstName").ignoreCase();
        } else {
            order = Sort.Order.asc("firstName").ignoreCase();
        }
        Sort sort = Sort.by(order);
        return repository.findAll(sort);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return repository.findByFirstNameIgnoreCase(firstName);
    }

    @Override
    public Employee update(Long employeeId, Employee employee) {
        Employee existEmployee = repository.findById(employeeId).get();
        existEmployee.setEmail(employee.getEmail());
        existEmployee.setFirstName(employee.getFirstName());
        existEmployee.setLastName(employee.getLastName());
        return repository.save(existEmployee);
    }

    @Override
    public List<Employee> saveAllAndFlush(List<Employee> employees) {
        return repository.saveAllAndFlush(employees);
    }

}