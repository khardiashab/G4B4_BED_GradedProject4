package com.learning.app.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.learning.app.entity.Employee;
import com.learning.app.repository.EmployeeRepository;

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
        return repository.findById(employeeId).get();
    }

    @Override
    public List<Employee> findAll(String sortOrder) {
        Direction dir;
        if (sortOrder.equalsIgnoreCase("asc")) {
            dir = Direction.ASC;
        } else if (sortOrder.equalsIgnoreCase("desc")) {
            dir = Direction.DESC;
        } else {
            throw new RuntimeException();
        }
        Sort sort = Sort.by(dir, "firstName");
        return repository.findAll(sort);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
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
    public List<Employee> saveAllAndFlush(List<Employee> employees)
    {
        return repository.saveAllAndFlush(employees);
    }

}