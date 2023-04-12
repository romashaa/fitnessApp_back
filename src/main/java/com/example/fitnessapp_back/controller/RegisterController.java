package com.example.fitnessapp_back.controller;

import com.example.fitnessapp_back.dto.UserDto;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.UserRepository;
import com.example.fitnessapp_back.service.CustomUserDetailsService;
import com.example.fitnessapp_back.service.UserService;
import com.example.fitnessapp_back.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    private void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
//        try {
//            Authentication authenticate = authenticationManager
//                    .authenticate(
//                            new UsernamePasswordAuthenticationToken(
//                                    userDto.getName(), userDto.getPassword()
//                            )
//                    );
//
//            User user = (User) authenticate.getPrincipal();
//            user.setPassword(null);
//            return ResponseEntity.ok()
//                    .header(
//                            HttpHeaders.AUTHORIZATION,
//                            jwtUtil.generateToken(user)
//                    )
//                    .body(user);
//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
    }


}
