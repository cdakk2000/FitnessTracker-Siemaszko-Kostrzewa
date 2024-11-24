package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface TrainingService {
    List<Training> findAllTrainings();
    Training createTraining(Training training);
    List<Training> getTrainingsByUserId(Long userId);
    List<Training> getTrainingsByActivityType(ActivityType activityType);
    List<Training> getTrainingsFinishedAfter(Date dateTo);
    Training updateTraining(Long id, Training training);
}
