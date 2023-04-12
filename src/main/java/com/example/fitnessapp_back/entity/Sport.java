package com.example.fitnessapp_back.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double calories;
}
