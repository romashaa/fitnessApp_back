package com.example.fitnessapp_back.dto;
import com.example.fitnessapp_back.enums.ActivityLevel;
import com.example.fitnessapp_back.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private String password;
    private double weight;
    private int height;
    private int age;
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;
    @Enumerated(EnumType.STRING)
    private Gender gender;


}