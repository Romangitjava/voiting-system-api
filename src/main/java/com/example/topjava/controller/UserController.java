package com.example.topjava.controller;

import com.example.topjava.domain.User;
import com.example.topjava.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> users() {
        return userRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Optional<User> getOne(@PathVariable("id") Long id) {
        return userRepository.findById(id);
    }
}
