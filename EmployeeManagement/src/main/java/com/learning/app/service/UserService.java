package com.learning.app.service;

import java.util.List;

import com.learning.app.entity.User;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    List<User> findAll();

}