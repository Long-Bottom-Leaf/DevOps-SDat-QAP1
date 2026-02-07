package com.project.services;

import com.project.fitnesstracker.Workout;
import com.project.fitnesstracker.WorkoutType;

import java.time.LocalDate;
import java.util.List;

public interface ProgressService {

    // Total metrics
        int totalWorkouts();
        int totalDuration();      // in minutes
        int totalCalories();

    // Filtered metrics
        int totalWorkoutsByType(WorkoutType type);
        int totalDurationByType(WorkoutType type);
        int totalCaloriesByType(WorkoutType type);

        int totalWorkoutsByDateRange(LocalDate start, LocalDate end);
        int totalDurationByDateRange(LocalDate start, LocalDate end);
        int totalCaloriesByDateRange(LocalDate start, LocalDate end);

    // Filtering for individual workouts
        List<Workout> filterByType(WorkoutType type);
        List<Workout> filterByDateRange(LocalDate start, LocalDate end);

}
