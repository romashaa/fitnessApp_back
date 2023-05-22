package com.example.fitnessapp_back.controller;


import com.example.fitnessapp_back.entity.Dish;
import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.dto.MealRequest;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.DishRepository;
import com.example.fitnessapp_back.repository.MealRepository;
import com.example.fitnessapp_back.repository.UserRepository;
import com.example.fitnessapp_back.service.MealService;
import com.example.fitnessapp_back.service.RecommendationSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/auth/meals")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MealController {
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final DishRepository dishRepository;
    private final MealService mealService;
    private final RecommendationSystem recommendationSystem;
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
    @DeleteMapping("deleteMeal/{email}")
    public void deleteMeal(@PathVariable String email, @RequestParam Long mealId){
        User user = userRepository.findByEmail(email).get();
        Meal m = mealRepository.findById(mealId).get();
        user.getMeals().remove(m);
        mealRepository.delete(m);
        userRepository.save(user);
    }
//    @GetMapping("mealsOfType/{email}")
//    public List<Meal> getMealsByTypeAndDate(@PathVariable String email, @RequestParam String date, @RequestParam MealType type){
//        User user = userRepository.findByEmail(email).get();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(date, formatter);
//        return mealRepository.findByUserAndDateAndMealType(user, localDate, type);
//    }
    @GetMapping("/{email}")
    public List<Meal> getMealsByDate(@PathVariable String email, @RequestParam String date){
        User user = userRepository.findByEmail(email).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return mealRepository.findByUserAndDate(user, localDate);
    }

    @GetMapping("/dayWithAllMeals/{email}")
    public List<List<Meal>> dayWithAllMeals(@PathVariable String email){
        User user = userRepository.findByEmail(email).get();
        return mealService.getMealListsForUserWithAllTypesPresent(user);
    }

    @GetMapping("/recommendation/{email}")
    public List<Dish> recommendedDishes(@PathVariable String email){
        User user = userRepository.findByEmail(email).get();
        List<Meal> existingMeals = (mealService.getMealListsForUserWithAllTypesPresent(user)).get(0);
        List<Dish> dishes = new ArrayList<>();
        for(Meal m: existingMeals){
            List<Dish> dishesToRecommend = recommendationSystem.getSimilarDishes(m.getDish(),2);
            if(!dishes.contains(dishRepository.findByDishName(dishesToRecommend.get(0).getDishName()))){
                dishes.add(dishesToRecommend.get(0));
            }else {
                dishes.add(dishesToRecommend.get(1));
            }
        }
        return dishes;
    }
    @GetMapping("/similarDish")
    public List<Dish> recommendedDishes(@RequestParam Long dishId){
        return recommendationSystem.getSimilarDishes(dishRepository.getById(dishId),3);
    }
}
