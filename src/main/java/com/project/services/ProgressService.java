package com.project.services;

import com.project.fitnesstracker.Workout;
import com.project.fitnesstracker.WorkoutType;

import java.time.LocalDate;
import java.util.List;

public interface ProgressService {

    // Total metrics
        int totalWorkouts();
        int totalDuration();
        int totalCalories();

    // Type-based metrics
        int totalWorkoutsByType(WorkoutType type);
        int totalDurationByType(WorkoutType type);
        int totalCaloriesByType(WorkoutType type);

    // Date-based metrics
        int totalWorkoutsByDateRange(LocalDate start, LocalDate end);
        int totalDurationByDateRange(LocalDate start, LocalDate end);
        int totalCaloriesByDateRange(LocalDate start, LocalDate end);

    // Combined (needed for goals)
        int totalWorkoutsByTypeAndDateRange(WorkoutType type, LocalDate start, LocalDate end);
        int totalDurationByTypeAndDateRange(WorkoutType type, LocalDate start, LocalDate end);
        int totalCaloriesByTypeAndDateRange(WorkoutType type, LocalDate start, LocalDate end);

    // Filtering (read-only)
        List<Workout> filterByType(WorkoutType type);
        List<Workout> filterByDateRange(LocalDate start, LocalDate end);
}

