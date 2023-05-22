package com.example.fitnessapp_back.service;

import com.example.fitnessapp_back.entity.Activity;
import com.example.fitnessapp_back.entity.Dish;
import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.enums.MealType;
import com.example.fitnessapp_back.repository.ActivityRepository;
import com.example.fitnessapp_back.repository.MealRepository;
import com.example.fitnessapp_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

//1)Порахувати кількість калорій за один прийом їжі(meal)
public int countMealCalories(User user, LocalDate date, MealType type){
    List<Meal> meals = mealRepository.findByUserAndDateAndMealType(user, date, type);
    int res = 0;
    for(Meal m: meals){
        res+=Math.round(m.getDish().getCalories()*0.01*m.getGrams());
    }
    return res;
}


    //Всі страви за день
    public List<Meal> dayMeals(User user, LocalDate day){
        return mealRepository.findByUserAndDate(user, day);
    }

   //2)Порахувати кількість спожитих калорій за день(по даті)

    public double countDayEatenCalories(User user, LocalDate date){
        List<Meal> meals = mealRepository.findByUserAndDate(user, date);
        double res = 0;
        for(Meal m: meals){
            res+=Math.round(m.getDish().getCalories()*0.01*m.getGrams());
        }
        return res;
    }
//3)Витрачені за день калорії

    public double countDaySpentCalories(User user, LocalDate date){
        List<Activity> activities = activityRepository.findByUserAndDate(user, date);
        double res = 0;
        for(Activity a: activities){
            res+=Math.round((a.getSport().getCalories()*(a.getDuration()/30)*user.getWeight()));
        }
        return res;
    }

    //4) загальна маса сттрав за день
     public double countTotalGrams(User user, LocalDate date){
         List<Meal> meals = mealRepository.findByUserAndDate(user, date);
         double res = 0;
         for(Meal m: meals){
             res+=m.getGrams();
         }
         return res;
     }



}
