package com.example.fitnessapp_back.controller;


import com.example.fitnessapp_back.dto.UserDto;
import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("myProfile/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;

    @PutMapping("/user/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User userDetails) {
        User updateUser = userRepository.findByEmail(email).get();
        updateUser.setAge(userDetails.getAge());
        updateUser.setGender(userDetails.getGender());
        updateUser.setActivityLevel(userDetails.getActivityLevel());
        updateUser.setHeight(userDetails.getHeight());
        updateUser.setWeight(userDetails.getWeight());

        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }
    @PutMapping("/user/{email}/countNorm")
    public ResponseEntity<User> setCaloriesNorm(@PathVariable String email, @RequestParam int norm){
        User updateUser = userRepository.findByEmail(email).get();
        updateUser.setCaloriesNorm(norm);
        userRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/user/{email}")
    public User getUser(@PathVariable String email){
        User user = userRepository.findByEmail(email).get();
        return user;
    }
    @GetMapping("/userDto/{email}")
    public UserDto getUserDto(@PathVariable String email){
        User user = userRepository.findByEmail(email).get();
        UserDto userDto = new UserDto();
        userDto.setActivityLevel(user.getActivityLevel());
        userDto.setAge(user.getAge());
        userDto.setGender(user.getGender());
        userDto.setHeight(user.getHeight());
        userDto.setWeight(user.getWeight());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

}
