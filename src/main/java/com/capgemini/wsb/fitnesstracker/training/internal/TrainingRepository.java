package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Date;

interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByUserId(Long userId);
    List<Training> findByActivityType(ActivityType activityType);
}
