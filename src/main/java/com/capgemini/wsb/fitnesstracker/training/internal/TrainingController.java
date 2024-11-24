package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody @Valid TrainingDtoUserId trainingDtoUserId) {
        Training training = trainingMapper.toEntity(trainingDtoUserId);
        Training savedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(savedTraining);
    }

    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDtoUserId trainingDto) {
        Training updatedTraining = trainingService.updateTraining(id, trainingMapper.toEntity(trainingDto));
        return trainingMapper.toDto(updatedTraining);
    }
}
