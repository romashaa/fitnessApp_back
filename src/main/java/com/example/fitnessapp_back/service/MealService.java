package com.example.fitnessapp_back.service;

import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

}
