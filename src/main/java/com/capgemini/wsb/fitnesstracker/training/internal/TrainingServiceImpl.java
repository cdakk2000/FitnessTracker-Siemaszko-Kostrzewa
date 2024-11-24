package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: Provide Impl
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;


    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }


    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public Training createTraining(Training training)
    {
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training id is already set");
        }
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsFinishedAfter(Date endTime) {
        return trainingRepository.findAll().stream()
                .filter(training -> training.getEndTime().after(endTime))
                .collect(Collectors.toList());
    }


    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    @Override
    public Training updateTraining(Long id, Training updatedTraining) {
        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Training not found for ID: " + id));

        if (updatedTraining.getStartTime() != null) existingTraining.setStartTime(updatedTraining.getStartTime());
        if (updatedTraining.getEndTime() != null) existingTraining.setEndTime(updatedTraining.getEndTime());
        if (updatedTraining.getActivityType() != null) existingTraining.setActivityType(updatedTraining.getActivityType());
        if (updatedTraining.getDistance() != 0) existingTraining.setDistance(updatedTraining.getDistance());
        if (updatedTraining.getAverageSpeed() != 0) existingTraining.setAverageSpeed(updatedTraining.getAverageSpeed());

        return trainingRepository.save(existingTraining);
    }
}


