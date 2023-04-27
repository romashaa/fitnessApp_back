package com.example.fitnessapp_back.controller;


import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.MealRequest;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.enums.MealType;
import com.example.fitnessapp_back.repository.DishRepository;
import com.example.fitnessapp_back.repository.MealRepository;
import com.example.fitnessapp_back.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/auth/meals")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MealController {
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final DishRepository dishRepository;
    @PostMapping("addMeal/{email}")
    public void addMealForUser(@PathVariable String email, @RequestBody MealRequest mealDetails){
        User user = userRepository.findByEmail(email).get();
        Meal meal = new Meal();
        meal.setUser(user);
        meal.setMealType(mealDetails.getMealType());
        meal.setGrams(mealDetails.getGrams());
        meal.setDate(mealDetails.getDate());
        meal.setTime(mealDetails.getTime());
        meal.setDish(dishRepository.findByDishName(mealDetails.getDishName()));
        mealRepository.save(meal);
    }
    @GetMapping("mealsOfType/{email}")
    public List<Meal> getMealsByTypeAndDate(@PathVariable String email, @RequestParam String date, @RequestParam MealType type){
        User user = userRepository.findByEmail(email).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return mealRepository.findByUserAndDateAndMealType(user, localDate, type);
    }
}
