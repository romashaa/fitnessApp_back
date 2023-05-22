package com.example.fitnessapp_back.entity;

import com.example.fitnessapp_back.enums.DishCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dishName;
    private String description;
    private double bilki;
    private double fats;
    private double vuhlevody;
    private int calories;
    private transient double distance;
    @Enumerated(EnumType.STRING)
    private DishCategory dishCategory;


}
