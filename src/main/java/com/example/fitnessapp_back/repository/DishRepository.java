package com.example.fitnessapp_back.repository;

import com.example.fitnessapp_back.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {
    Dish findByDishName(String name);
}
