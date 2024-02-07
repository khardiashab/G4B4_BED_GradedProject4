package com.learning.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.app.entity.Employee;
import com.learning.app.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee findById(@PathVariable("employeeId") Long employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping("")
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PostMapping("/{employeeId}")
    public Employee update(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee){
        employee.setId(employeeId);
        return employeeService.update(employeeId, employee);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteBYId(@PathVariable("employeeId") Long employeeId) {
        return employeeService.deleteById(employeeId);
    }

    @GetMapping("/search/{firstName}")
    public List<Employee> findByFirstName(@PathVariable("firstName") String firstName) {
        return employeeService.findByFirstName(firstName);
    }

    @GetMapping("/sort")
    public List<Employee> findAllBySort(
            @RequestParam(name = "order", defaultValue = "asc") String sortOrder) {
        return employeeService.findAll(sortOrder);
    }
}