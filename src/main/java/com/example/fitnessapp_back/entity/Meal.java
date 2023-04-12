package com.example.fitnessapp_back.entity;

import com.example.fitnessapp_back.enums.MealType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private LocalDate date;
    private LocalTime time;
    private int grams;
    @ManyToOne
    @JoinColumn(name = "dish_dish_id")
    private Dish dish;
    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;
}
