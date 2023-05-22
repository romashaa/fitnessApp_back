package com.example.fitnessapp_back.controller;

import com.example.fitnessapp_back.entity.Dish;
import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.enums.MealType;
import com.example.fitnessapp_back.repository.MealRepository;
import com.example.fitnessapp_back.repository.UserRepository;
import com.example.fitnessapp_back.service.InfoService;
import com.example.fitnessapp_back.service.RecommendationSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/auth/info")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class InfoController {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final InfoService infoService;

    @GetMapping("/dayNorm/{email}")
    public int norm(@PathVariable String email){
        User user = userRepository.findByEmail(email).get();
        return user.getCaloriesNorm();
    }

    @GetMapping("/dayReceived/{email}")
    public double countDayEatenCalories(@PathVariable String email, @RequestParam String date){
        User user = userRepository.findByEmail(email).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return infoService.countDayEatenCalories(user, localDate);
    }
    @GetMapping("/dayTotalGrams/{email}")
    public double countDayTotalGrams(@PathVariable String email, @RequestParam String date){
        User user = userRepository.findByEmail(email).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return infoService.countTotalGrams(user, localDate);
    }

    @GetMapping("/mealReceived/{email}")
    public double countDayEatenCalories(@PathVariable String email, @RequestParam String date, @RequestParam MealType type){
        User user = userRepository.findByEmail(email).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return infoService.countMealCalories(user, localDate, type);
    }
    @GetMapping("/mealsType/{email}")
    public List<Meal> mealsOfType(@PathVariable String email, @RequestParam String date, @RequestParam MealType type){
        User user = userRepository.findByEmail(email).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<Meal> meals = mealRepository.findByUserAndDateAndMealType(user, localDate, type);

        return meals;
    }

    @GetMapping("/daySpent/{email}")
    public double countDaySpentCalories(@PathVariable String email, @RequestParam String date){
        User user = userRepository.findByEmail(email).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return infoService.countDaySpentCalories(user, localDate);
    }



}
