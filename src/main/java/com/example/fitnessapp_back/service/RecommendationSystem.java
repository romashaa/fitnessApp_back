package com.example.fitnessapp_back.service;

import com.example.fitnessapp_back.entity.Dish;
import com.example.fitnessapp_back.repository.DishRepository;
import com.example.fitnessapp_back.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecommendationSystem {
    private final MealRepository mealRepository;
    private final DishRepository dishRepository;
    public List<Dish> getSimilarDishes(Dish chosenDish, int numRecommendations) {
        List<Dish> similarDishes = new ArrayList<>();
        List<Dish> dishes = dishRepository.findAll();
        // Calculate Euclidean distance and filter by category
        for (Dish dish : dishes) {
            if (dish.getDishCategory().equals(chosenDish.getDishCategory()) &&
                    !dish.getDishName().equals(chosenDish.getDishName())) {
                double distance = calculateEuclideanDistance(chosenDish, dish);
                dish.setDistance(distance);
                similarDishes.add(dish);
            }
        }
        // Sort dishes based on distance
        similarDishes.sort((d1, d2) -> Double.compare(d1.getDistance(), d2.getDistance()));

        // Retrieve top N similar dishes
        if (similarDishes.size() > numRecommendations) {
            similarDishes = similarDishes.subList(0, numRecommendations);
        }
        return similarDishes;
    }

    private double calculateEuclideanDistance(Dish dish1, Dish dish2) {
        double proteinDiff = dish1.getBilki() - dish2.getBilki();
        double fatDiff = dish1.getFats() - dish2.getFats();
        double carbohydrateDiff = dish1.getVuhlevody() - dish2.getVuhlevody();
        double caloriesDiff = dish1.getCalories()-dish2.getCalories();

        return Math.sqrt(proteinDiff * proteinDiff + fatDiff * fatDiff + carbohydrateDiff * carbohydrateDiff + caloriesDiff*caloriesDiff);

    }
}
