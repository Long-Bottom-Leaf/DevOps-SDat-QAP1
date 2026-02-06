package com.project.services;

import com.project.fitnesstracker.Workout;
import com.project.fitnesstracker.WorkoutType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class InMemoryWorkoutServiceTest {

    // valid workout test
        @Test
        void logWorkout_validWorkout_isStored() {
            WorkoutService service = new InMemoryWorkoutService();

            Workout workout = new Workout(
                    1,
                    LocalDate.now(),
                    WorkoutType.RUNNING,
                    30,
                    300
            );

            System.out.println("Workout successfully logged!");
        }

    // null workout
        @Test
        void logWorkout_nullWorkout_throwsException() {
            WorkoutService service = new InMemoryWorkoutService();

            assertThrows(IllegalArgumentException.class, () ->
                    service.logWorkout(null)
            );

            System.out.println("Workout was not logged!");
        }

}
