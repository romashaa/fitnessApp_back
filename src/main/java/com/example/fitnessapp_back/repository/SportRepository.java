package com.example.fitnessapp_back.repository;

import com.example.fitnessapp_back.entity.Sport;
import com.example.fitnessapp_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    Sport findByName(String name);
}
