package com.example.fitnessapp_back.controller;

import com.example.fitnessapp_back.entity.Dish;
import com.example.fitnessapp_back.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DishController {
    private final DishRepository dishRepository;

    @PostMapping("/addDish")
    public void addDish(@RequestBody Dish dish){
        dishRepository.save(dish);
    }

    @GetMapping("/all")
    public List<Dish> getDishes(){
        return dishRepository.findAll();
    }

//    @GetMapping("/search")
//    public List<String> searchDish(@RequestParam("term") String term){
//        List<String> names = new ArrayList<>();
//        List<Dish> dishes = dishRepository.findAll();
//        for(Dish dish: dishes){
//            names.add(dish.getDishName());
//        }
//        List<String> res = new ArrayList<>();
//        for (String searchTerm : names) {
//            if (searchTerm.toLowerCase().startsWith(term.toLowerCase())) {
//                res.add(searchTerm);
//            }
//        }
//        return res;
//    }

}
