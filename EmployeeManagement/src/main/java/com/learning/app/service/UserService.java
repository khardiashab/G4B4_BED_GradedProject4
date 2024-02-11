package com.learning.app.service;

import java.util.List;
import java.util.Optional;

import com.learning.app.entity.User;

public interface UserService {

    User save(User user);

    Optional<User> findByUsername(String username);

    List<User> findAll();

}