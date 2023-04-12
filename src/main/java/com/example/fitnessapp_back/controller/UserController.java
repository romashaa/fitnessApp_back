package com.example.fitnessapp_back.controller;


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
        updateUser.setGoal(userDetails.getGoal());
        updateUser.setHeight(userDetails.getHeight());
        updateUser.setWeight(userDetails.getWeight());

        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/user/{email}")
    public User getUser(@PathVariable String email){
        User user = userRepository.findByEmail(email).get();
        return user;
    }

}
