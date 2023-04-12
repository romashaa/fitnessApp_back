package com.example.fitnessapp_back.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;
    private String dishName;
    private String description;
    private double bilki;
    private double fats;
    private double vuhlevody;
    private int calories;

}
