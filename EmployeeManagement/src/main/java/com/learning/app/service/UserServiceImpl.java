package com.learning.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.app.entity.User;
import com.learning.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    
    private final UserRepository repository;
    // private final PasswordEncoder encoder;

    public User save(User user){
        // change the password to endcoded password
        // user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User findByUsername(String username){
        return repository.findByUsername(username).get();
    }

    public List<User> findAll(){
        return repository.findAll();
    }
}
