package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;
    private final TrainingRepository trainingRepository;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<Training> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @GetMapping("/finished/{dateTo}")
    public ResponseEntity<List<TrainingDto>> getTrainingsFinishedAt(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) {
        try {
            List<TrainingDto> trainings = trainingService.getTrainingsFinishedAfter(dateTo).stream().map(trainingMapper::toDto).collect(toList());
            return ResponseEntity.ok(trainings);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/activityType")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        try {
            List<TrainingDto> trainings = trainingService.getTrainingsByActivityType(activityType).stream().map(trainingMapper::toDto).collect(toList());
            return ResponseEntity.ok(trainings);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
