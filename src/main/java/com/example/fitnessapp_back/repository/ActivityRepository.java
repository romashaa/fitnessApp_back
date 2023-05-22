package com.example.fitnessapp_back.repository;

import com.example.fitnessapp_back.entity.Activity;
import com.example.fitnessapp_back.entity.Sport;
import com.example.fitnessapp_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByUserAndDate(User user, LocalDate date);
}
