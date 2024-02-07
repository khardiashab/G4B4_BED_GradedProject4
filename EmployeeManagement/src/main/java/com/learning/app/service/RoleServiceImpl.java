package com.learning.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.app.entity.Role;
import com.learning.app.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {

    private final RoleRepository repository;

    public Role save(Role role) {
        return repository.save(role);
    }

    public List<Role> findAll() {
        return repository.findAll();
    }
}
