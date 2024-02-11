package com.learning.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.app.config.CustomPasswordEncoder;
import com.learning.app.entity.Role;
import com.learning.app.entity.User;
import com.learning.app.exception.NotAValidRoleExcetpion;
import com.learning.app.repository.UserRepository;
import com.learning.app.service.RoleService;
import com.learning.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final CustomPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User save(User user) {
        // Ensure that the roles associated with the user are managed
        for (Role role : user.getRoles()) {
            // If role has not id or id = 0 then it create new role.
            if (role.getId() == null || role.getId() != 0) {
                roleService.save(role);
            } else {
                // If the role has an ID, check if it exists in the database
                roleService.findById(role.getId()).orElseThrow(() -> new NotAValidRoleExcetpion(role.getId()));
            }
        }

        // Change the password to encoded password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Persist the user entity
        return repository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}