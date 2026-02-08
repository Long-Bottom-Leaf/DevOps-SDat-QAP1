package com.project.services;

import com.project.fitnesstracker.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InMemoryGoalServiceTest {

    // initializations
    private ProgressService mockProgressService;
    private GoalService goalService;

    @BeforeEach
    void setUp() {
        mockProgressService = mock(ProgressService.class);
        goalService = new InMemoryGoalService(mockProgressService);
    }

    // weekly workout count goal met
        @Test
        void weeklyWorkoutCountGoalMet() {
            Goal goal = new Goal(
                    "Run 3 times this week",
                    WorkoutType.RUNNING,
                    GoalMetric.WORKOUT_COUNT,
                    GoalTimeframe.WEEKLY,
                    3
            );

            when(mockProgressService.totalWorkoutsByTypeAndDateRange(
                    eq(WorkoutType.RUNNING),
                    any(LocalDate.class),
                    any(LocalDate.class) // Ignoring dates for this test
            )).thenReturn(4);

            assertTrue(goalService.isGoalMet(goal));

            System.out.print("Workout type count goal is met!\n");
        }

    // weekly workout count goal NOT met
        @Test
        void weeklyWorkoutCountGoalNotMet() {
            Goal goal = new Goal(
                    "Run 5 times this week",
                    WorkoutType.RUNNING,
                    GoalMetric.WORKOUT_COUNT,
                    GoalTimeframe.WEEKLY,
                    5
            );

            when(mockProgressService.totalWorkoutsByTypeAndDateRange(
                    eq(WorkoutType.RUNNING),
                    any(LocalDate.class),
                    any(LocalDate.class)
            )).thenReturn(3);

            assertFalse(goalService.isGoalMet(goal));

            System.out.print("Workout type count goal has not been met!\n");
        }

    // monthly duration goal met
        @Test
        void monthlyDurationGoalMet() {
            Goal goal = new Goal(
                    "Cycle 300 minutes this month",
                    WorkoutType.CYCLING,
                    GoalMetric.TOTAL_DURATION,
                    GoalTimeframe.MONTHLY,
                    300
            );

            when(mockProgressService.totalDurationByTypeAndDateRange(
                    eq(WorkoutType.CYCLING),
                    any(LocalDate.class),
                    any(LocalDate.class)
            )).thenReturn(320);

            assertTrue(goalService.isGoalMet(goal));

            System.out.print("Workout duration goal is met!\n");
        }

    // monthly calories goal NOT met
        @Test
        void calorieGoalNotMet() {
            Goal goal = new Goal(
                    "Burn 2000 calories running",
                    WorkoutType.RUNNING,
                    GoalMetric.TOTAL_CALORIES,
                    GoalTimeframe.MONTHLY,
                    2000
            );

            when(mockProgressService.totalCaloriesByTypeAndDateRange(
                    eq(WorkoutType.RUNNING),
                    any(LocalDate.class),
                    any(LocalDate.class)
            )).thenReturn(1500);

            assertFalse(goalService.isGoalMet(goal));

            System.out.print("Calories burnt goal has not been met!\n");
        }

    // ensure goals are registered
        @Test
        void registerGoalStoresGoal() {
            Goal goal = new Goal(
                    "Run once",
                    WorkoutType.RUNNING,
                    GoalMetric.WORKOUT_COUNT,
                    GoalTimeframe.WEEKLY,
                    1
            );

            goalService.registerGoal(goal);

            assertEquals(1, goalService.getAllGoals().length);

            System.out.print("Goals are properly registered!");
        }

}
