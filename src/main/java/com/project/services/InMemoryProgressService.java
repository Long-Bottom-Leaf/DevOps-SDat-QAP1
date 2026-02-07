package com.project.services;

import com.project.fitnesstracker.Workout;
import com.project.fitnesstracker.WorkoutType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;     // collectors has useful operations for accumulating and summarizing elements

public class InMemoryProgressService implements ProgressService {

    private final WorkoutService workoutService;

    public InMemoryProgressService(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // total metrics
        @Override
        public int totalWorkouts() {
            return workoutService.getAllWorkouts().size();
        }

        @Override
        public int totalDuration() {
            return workoutService.getAllWorkouts()
                    .stream()
                    .mapToInt(Workout::getDuration)
                    .sum();
        }

        @Override
        public int totalCalories() {
            return workoutService.getAllWorkouts()
                    .stream()
                    .mapToInt(Workout::getCaloriesBurned)
                    .sum();
        }

    // totals by type of workout
        @Override
        public int totalWorkoutsByType(WorkoutType type) {
            return (int) workoutService.getAllWorkouts()
                    .stream()
                    .filter(workout -> workout.getType() == type)
                    .count();
        }

        @Override
        public int totalDurationByType(WorkoutType type) {
            return workoutService.getAllWorkouts()
                    .stream()
                    .filter(workout -> workout.getType() == type)
                    .mapToInt(Workout::getDuration)
                    .sum();
        }

        @Override
        public int totalCaloriesByType(WorkoutType type) {
            return workoutService.getAllWorkouts()
                    .stream()
                    .filter(workout -> workout.getType() == type)
                    .mapToInt(Workout::getCaloriesBurned)
                    .sum();
        }

    // ===== Totals by date range =====
        @Override
        public int totalWorkoutsByDateRange(LocalDate start, LocalDate end) {
            return (int) workoutService.getAllWorkouts()
                    .stream()
                    .filter(workout -> !workout.getDate().isBefore(start) && !workout.getDate().isAfter(end))
                    .count();
        }

        @Override
        public int totalDurationByDateRange(LocalDate start, LocalDate end) {
            return workoutService.getAllWorkouts()
                    .stream()
                    .filter(workout -> !workout.getDate().isBefore(start) && !workout.getDate().isAfter(end))
                    .mapToInt(Workout::getDuration)
                    .sum();
        }

        @Override
        public int totalCaloriesByDateRange(LocalDate start, LocalDate end) {
            return workoutService.getAllWorkouts()
                    .stream()
                    .filter(workout -> !workout.getDate().isBefore(start) && !workout.getDate().isAfter(end))
                    .mapToInt(Workout::getCaloriesBurned)
                    .sum();
        }

    // ===== Filters =====
        @Override
        public List<Workout> filterByType(WorkoutType type) {
            return workoutService.getAllWorkouts()
                    .stream()
                    .filter(workout -> workout.getType() == type)
                    .collect(Collectors.toList());
        }

        @Override
        public List<Workout> filterByDateRange(LocalDate start, LocalDate end) {
            return workoutService.getAllWorkouts()
                    .stream()
                    .filter(workout -> !workout.getDate().isBefore(start) && !workout.getDate().isAfter(end))
                    .collect(Collectors.toList());
        }
}
