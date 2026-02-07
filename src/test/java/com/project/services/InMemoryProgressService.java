package com.project.services;

import com.project.fitnesstracker.Workout;
import com.project.fitnesstracker.WorkoutType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InMemoryProgressServiceTest {

    // initializations
    private WorkoutService mockWorkoutService;
    private ProgressService progressService;

    private Workout workout1;
    private Workout workout2;
    private Workout workout3;

    // ensure workouts exist for testing
        @BeforeEach
        void setUp() {
            mockWorkoutService = mock(WorkoutService.class);
            progressService = new InMemoryProgressService(mockWorkoutService);

            // Sample workouts
            workout1 = new Workout(
                    1,
                    LocalDate.of(2026, 2, 1),
                    WorkoutType.RUNNING,
                    30,
                    200
            );

            workout2 = new Workout(
                    2,
                    LocalDate.of(2026, 2, 2),
                    WorkoutType.CYCLING,
                    45,
                    400
            );

            workout3 = new Workout(
                    3,
                    LocalDate.of(2026, 2, 5),
                    WorkoutType.RUNNING,
                    20,
                    150
            );

            // Mock the WorkoutService to return these workouts
            when(mockWorkoutService.getAllWorkouts()).thenReturn(List.of(workout1, workout2, workout3));
        }

    // correct total workouts
        @Test
        void testTotalWorkouts() {
            assertEquals(3, progressService.totalWorkouts());

            System.out.print("Total workouts of 3 is correct!");
        }

    // test correct total duration
        @Test
        void testTotalDuration() {
            assertEquals(30 + 45 + 20, progressService.totalDuration());

            System.out.print("Total duration of 95 minutes is correct!");
        }

    // test correct total calories burned
        @Test
        void testTotalCalories() {
            assertEquals(200 + 400 + 150, progressService.totalCalories());

            System.out.print("Total calories burned of 750 calories is correct!");
        }

    // test filter by workout type
        @Test
        void testTotalWorkoutsByType() {
            assertEquals(2, progressService.totalWorkoutsByType(WorkoutType.RUNNING));
            assertEquals(1, progressService.totalWorkoutsByType(WorkoutType.CYCLING));

            System.out.print("Running = " + progressService.totalWorkoutsByType(WorkoutType.RUNNING) + " is correct!\n");
            System.out.print("Cycling = " + progressService.totalWorkoutsByType(WorkoutType.CYCLING) + " is correct!");
        }

    // filter by date range
        @Test
        void testFilterByDateRange() {
            List<Workout> filtered = progressService.filterByDateRange(
                    LocalDate.of(2026, 2, 1),
                    LocalDate.of(2026, 2, 5)
            );

            assertEquals(3, filtered.size()); // workout1 and workout3
            assertTrue(filtered.contains(workout1));
            assertTrue(filtered.contains(workout3));

            System.out.print("Three workouts were found correctly!");
        }

    // filter by type
        @Test
        void testFilterByType() {
            List<Workout> runningWorkouts = progressService.filterByType(WorkoutType.RUNNING);

            assertEquals(2, runningWorkouts.size());
            assertTrue(runningWorkouts.contains(workout1));
            assertTrue(runningWorkouts.contains(workout3));

            System.out.print("Two workouts were found for the running type!\n");
        }

    // verify other workout service calls
        @Test
        void verifyWorkoutServiceCalled() {
            progressService.totalWorkouts();
            progressService.totalCalories();

            verify(mockWorkoutService, times(2)).getAllWorkouts(); // called once per method

            System.out.print("totalWorkouts and totalCalories handled correctly!");
        }
}
