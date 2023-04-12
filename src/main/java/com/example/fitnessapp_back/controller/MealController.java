package com.example.fitnessapp_back.controller;


import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/meals")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MealController {
    private final UserRepository userRepository;
    @PutMapping("addMeal/{email}")
    public void addMealForUser(@PathVariable String email, @RequestBody Meal mealDetails){
        User user = userRepository.findByEmail(email).get();
        Meal meal = new Meal();
        meal.setMealType(mealDetails.getMealType());
        meal.setGrams(mealDetails.getGrams());
        meal.setDate(mealDetails.getDate());
        meal.setTime(mealDetails.getTime());
        user.getMeals().add(meal);
        userRepository.save(user);
    }
}
