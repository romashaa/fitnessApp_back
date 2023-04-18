package com.example.fitnessapp_back.entity;

import com.example.fitnessapp_back.enums.MealType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class MealRequest {
    private MealType mealType;
    private LocalDate date;
    private LocalTime time;
    private int grams;
    private String dishName;
}
