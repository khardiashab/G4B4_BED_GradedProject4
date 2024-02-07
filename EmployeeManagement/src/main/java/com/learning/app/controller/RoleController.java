package com.learning.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.app.entity.Role;
import com.learning.app.service.RoleServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleServiceImpl roleService;

    @GetMapping("")
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @PostMapping("")
    public Role save(Role role) {
        return roleService.save(role);
    }
}