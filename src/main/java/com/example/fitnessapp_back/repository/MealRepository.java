package com.example.fitnessapp_back.repository;

import com.example.fitnessapp_back.entity.Meal;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.enums.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal,Long> {

    List<Meal> findByUserAndDate(User user, LocalDate date);
    List<Meal> findByUserAndDateAndMealType(User user, LocalDate date, MealType mealType);

    @Query("SELECT m FROM Meal m " +
            "WHERE m.user = :user " +
            "AND m.date IN (" +
            "    SELECT m1.date FROM Meal m1 " +
            "    WHERE m1.user = :user " +
            "    GROUP BY m1.date " +
            "    HAVING COUNT(DISTINCT m1.mealType) = (SELECT COUNT(DISTINCT m2.mealType) FROM Meal m2 WHERE m2.user = :user)) " +
            "ORDER BY m.date")
    List<Meal> findMealsForUserWithAllTypesPresent(@Param("user") User user);

}
