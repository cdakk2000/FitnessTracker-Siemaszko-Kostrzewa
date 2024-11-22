package com.capgemini.wsb.fitnesstracker.training.api;

import java.util.Date;

public class TrainingDto {
    private Long id;
    private UserDto user;          // Zagnieżdżony obiekt użytkownika
    private Date startTime;        // Czas rozpoczęcia treningu
    private Date endTime;          // Czas zakończenia treningu
    private String activityType;   // Typ aktywności (np. Running, Cycling)
    private Double distance;       // Dystans
    private Double averageSpeed;   // Średnia prędkość

    // Konstruktor z wszystkimi polami
    public TrainingDto(
            Long id,
            UserDto user,
            Date startTime,
            Date endTime,
            String activityType,
            Double distance,
            Double averageSpeed
    ) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    // Gettery i Settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    // Klasa UserDto jako zagnieżdżona
    public static class UserDto {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;

        // Konstruktor
        public UserDto(Long id, String firstName, String lastName, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        // Gettery i Settery
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}