package com.example.fitnessapp_back.service;

import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.enums.MealType;
import com.example.fitnessapp_back.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;

    public void addMeal(Meal meal){
        mealRepository.save(meal);
    }
    public List<Meal> findByUserAndDate(User user, LocalDate date){
           return mealRepository.findByUserAndDate(user, date);
    }

public List<List<Meal>> getMealListsForUserWithAllTypesPresent(User user) {
    List<Meal> meals = mealRepository.findMealsForUserWithAllTypesPresent(user);
    Map<LocalDate, List<Meal>> mealMap = meals.stream().collect(Collectors.groupingBy(Meal::getDate));
    return new ArrayList<>(mealMap.values());
}

}
