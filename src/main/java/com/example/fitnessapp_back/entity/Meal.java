package com.example.fitnessapp_back.entity;

import com.example.fitnessapp_back.enums.MealType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    private LocalDate date;
    private LocalTime time;
    private int grams;

    @OneToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
