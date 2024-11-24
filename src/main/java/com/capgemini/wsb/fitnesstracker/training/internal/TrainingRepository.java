package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .toList();
    }
    List<Training> findByActivityType(ActivityType activityType);
}
