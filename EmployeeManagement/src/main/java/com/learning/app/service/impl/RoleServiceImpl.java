package com.learning.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.app.entity.Role;
import com.learning.app.exception.NotAValidRoleExcetpion;
import com.learning.app.repository.RoleRepository;
import com.learning.app.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role save(Role role) {
        role.setName(role.getName().trim().toUpperCase());
        if(role.getName().equals("USER") || role.getName().equals("ADMIN")){
            return repository.save(role);
        } else {
            throw new NotAValidRoleExcetpion(role.getName());
        }
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    public Optional<Role> findById(int roleId){
        return repository.findById(roleId);
    }
}
