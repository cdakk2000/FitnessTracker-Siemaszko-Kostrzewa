package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface TrainingService {
    List<Training> findAllTrainings();
    List<TrainingDto> getTrainingsByUserId(Long userId);
    List<Training> getTrainingsByActivityType(ActivityType activityType);
    List<Training> getTrainingsFinishedAfter(Date dateTo);
}
