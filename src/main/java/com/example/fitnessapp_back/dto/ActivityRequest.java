package com.example.fitnessapp_back.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ActivityRequest {
    private int duration;
    private LocalDate date;
    private LocalTime time;
    private String sportName;
}
