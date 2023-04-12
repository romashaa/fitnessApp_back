package com.example.fitnessapp_back.service;


import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.UserRepository;
import com.example.fitnessapp_back.util.CustomPasswordEncoder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOpt = userRepository.findByEmail(username);

        return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }


}
