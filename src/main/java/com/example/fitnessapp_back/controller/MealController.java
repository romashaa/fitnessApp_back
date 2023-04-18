package com.example.fitnessapp_back.controller;


import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.MealRequest;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.DishRepository;
import com.example.fitnessapp_back.repository.MealRepository;
import com.example.fitnessapp_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
