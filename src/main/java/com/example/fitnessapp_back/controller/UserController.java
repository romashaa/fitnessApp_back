package com.example.fitnessapp_back.controller;

import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    private User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
