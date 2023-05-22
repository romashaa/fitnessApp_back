package com.example.fitnessapp_back.controller;


import com.example.fitnessapp_back.entity.Activity;
import com.example.fitnessapp_back.dto.ActivityRequest;
import com.example.fitnessapp_back.entity.Sport;
import com.example.fitnessapp_back.entity.User;
import com.example.fitnessapp_back.repository.ActivityRepository;
import com.example.fitnessapp_back.repository.SportRepository;
import com.example.fitnessapp_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/auth/sport")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ActivityController {
    private final SportRepository sportRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;


    @PostMapping("/addActivity/{email}")
    public void addActivity(@PathVariable String email, @RequestBody ActivityRequest activityRequest){
        User user = userRepository.findByEmail(email).get();
        Activity activity = new Activity();
        activity.setDate(activityRequest.getDate());
        activity.setDuration(activityRequest.getDuration());
        activity.setTime(activityRequest.getTime());
        activity.setUser(user);
        activity.setSport(sportRepository.findByName(activityRequest.getSportName()));
        activityRepository.save(activity);
    }

    @GetMapping("/{email}")
    public List<Activity> getAllActivities(@PathVariable String email, @RequestParam String date){
        User user = userRepository.findByEmail(email).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return activityRepository.findByUserAndDate(user, localDate);
    }
    @GetMapping("/all")
    public List<Sport> getAllSports(){
        return sportRepository.findAll();
    }
}
