package com.project.services;

import com.project.fitnesstracker.Workout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// in memory service for testing purposes

public class InMemoryWorkoutService implements WorkoutService {

    // initialize list
        private final List<Workout> workouts = new ArrayList<>();

    // null workout check
    @Override
        public void logWorkout(Workout workout) {
            if (workout == null) {
                throw new IllegalArgumentException("Workout cannot be empty!");
            }

            workouts.add(workout);
        }

    // return unmodifiable list for security
    @Override
        public List<Workout> getAllWorkouts() {
            return Collections.unmodifiableList(workouts);
        }
}
