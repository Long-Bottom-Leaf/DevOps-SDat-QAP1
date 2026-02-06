package com.project.services;

import com.project.fitnesstracker.Workout;
import java.util.List;

public interface WorkoutService {

    void logWorkout(Workout workout);

    List<Workout> getAllWorkouts();
}