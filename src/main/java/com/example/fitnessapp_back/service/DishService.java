package com.example.fitnessapp_back.service;

import com.example.fitnessapp_back.entity.Dish;
import com.example.fitnessapp_back.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public void createDish(Dish dish){
        dishRepository.save(dish);
    }

}
