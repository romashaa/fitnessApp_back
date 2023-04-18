package com.example.fitnessapp_back.service;

import com.example.fitnessapp_back.entity.Dish;
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
public class InfoService {
    private final MealRepository mealRepository;


////1)Порахувати кількість калорій за один прийом їжі(meal)
//public double countMealCalories(Meal meal){
//    double calories = meal.getDish().getCalories()/100;
//    return meal.getGrams()*calories;
//}

    //Всі страви за день
    public List<Meal> dayMeals(User user, LocalDate day){
        return mealRepository.findByUserAndDate(user, day);
    }

//2)Порахувати кількість спожитих калорій за день(по даті)

//    public double countDayEatenCalories(User user, LocalDate date){
//        List<Meal> meals = mealRepository.findByUserAndDate(user, date);
//        double res = 0;
//        for(Meal m: meals){
//            res+=countMealCalories(m);
//        }
//        return res;
//    }
//3)Знайти денну норму норму калорій


}
