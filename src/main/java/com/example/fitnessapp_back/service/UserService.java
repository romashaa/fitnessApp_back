package com.example.fitnessapp_back.service;

import com.example.fitnessapp_back.dto.UserDto;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.UserRepository;
import com.example.fitnessapp_back.util.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepo;
    private final CustomPasswordEncoder customPasswordEncoder;

//    public Optional<User> findUserByUsername(String username) {
//        return userRepo.findByName(username);
//    }

    public void createUser(UserDto userDto) {
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        String encodedPassword = customPasswordEncoder.getPasswordEncoder().encode(userDto.getPassword());
        newUser.setPassword(encodedPassword);
        userRepo.save(newUser);
    }
}