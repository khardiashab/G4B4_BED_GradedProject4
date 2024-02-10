package com.learning.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.app.entity.User;
import com.learning.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }
}