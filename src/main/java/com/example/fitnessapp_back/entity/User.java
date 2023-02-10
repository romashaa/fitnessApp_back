package com.example.fitnessapp_back.entity;

import com.example.fitnessapp_back.enums.Gender;
import com.example.fitnessapp_back.enums.Goals;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String username;
    private double weight;
    private int height;
    private int birthYear;
//    @Enumerated(EnumType.STRING)
//    private Goals goal;
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
}
