package com.capgemini.wsb.fitnesstracker.training.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
//@AllArgsConstructor
public class TrainingDto {
//    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date startTime;
    private Date endTime;
//    private final String name;
    private String activityType;
    private Double distance;
    private Double averageSpeed;


    public TrainingDto(
//            Long id,
            Long userId,
            String firstName,
            String lastName,
            String email,
            Date startTime,
            Date endTime,
            String activityType,
            Double distance,
            Double averageSpeed
    ) {
//        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}



