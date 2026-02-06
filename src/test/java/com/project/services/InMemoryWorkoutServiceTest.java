package com.project.services;

import com.project.fitnesstracker.Workout;
import com.project.fitnesstracker.WorkoutType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

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

    // log 2 workouts
        @Test
        void logWorkout_validWorkoutCount2_isStored() {
            WorkoutService service = new InMemoryWorkoutService();

            Workout workout1 = new Workout(
                    1,
                    LocalDate.now(),
                    WorkoutType.RUNNING,
                    30,
                    300
            );

            Workout workout2 = new Workout(
                    2,
                    LocalDate.now(),
                    WorkoutType.CYCLING,
                    40,
                    500
            );

            System.out.println("Workouts successfully logged! " + workout1.getType() + " / " + workout2.getType());
        }

    // ensure list cannot be modified
        @Test
        void getAllWorkouts_listIsUnmodifiable() {
            WorkoutService service = new InMemoryWorkoutService();

            Workout workout = new Workout(
                    1,
                    LocalDate.now(),
                    WorkoutType.RUNNING,
                    30,
                    300
            );

            service.logWorkout(workout);

            List<Workout> workouts = service.getAllWorkouts();

            // attempt to add workout to list after list is returned
            assertThrows(UnsupportedOperationException.class, () ->
                    workouts.add(workout)
            );

            System.out.print("Cannot add a new workout at this time!");
        }
}
