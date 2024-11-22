package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingDto toDto(Training training) {
        return new TrainingDto(
//                training.getId(),
                training.getUser().getId(),
                training.getUser().getFirstName(),
                training.getUser().getLastName(),
                training.getUser().getEmail(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType().getDisplayName(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    public Training toEntity(TrainingDto trainingDto) {
        // Zakładając, że obiekt User i ActivityType zostaną dostarczone w serwisie
        throw new UnsupportedOperationException("Metoda do konwersji DTO na encję wymaga implementacji.");
    }

}