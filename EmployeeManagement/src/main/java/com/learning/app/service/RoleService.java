package com.learning.app.service;

import java.util.List;
import java.util.Optional;

import com.learning.app.entity.Role;

public interface RoleService {

    Role save(Role role);

    List<Role> findAll();

    public Optional<Role> findById(int roleId);

}