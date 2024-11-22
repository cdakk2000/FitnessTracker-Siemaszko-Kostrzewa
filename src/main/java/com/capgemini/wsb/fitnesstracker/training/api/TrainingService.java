package com.capgemini.wsb.fitnesstracker.training.api;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainingService {
    List<Training> findAllTrainings();
}
