package com.example.fitnessapp_back.repository;

import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.enums.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal,Long> {


    List<Meal> findByUserAndDate(User user, LocalDate date);
    List<Meal> findByUserAndDateAndMealType(User user, LocalDate date, MealType mealType);
}
