package com.project.services;

import com.project.fitnesstracker.Goal;
import com.project.fitnesstracker.GoalMetric;
import com.project.fitnesstracker.GoalTimeframe;
import com.project.fitnesstracker.WorkoutType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryGoalService implements GoalService {

    private final ProgressService progressService;
    private final List<Goal> goals = new ArrayList<>();

    public InMemoryGoalService(ProgressService progressService) {
        this.progressService = progressService;
    }

    // register goal
        @Override
        public void registerGoal(Goal goal) {
            goals.add(goal);
        }

    // check if goal is met
        @Override
        public boolean isGoalMet(Goal goal) {

            LocalDate end = LocalDate.now();
            LocalDate start;

            if (goal.getTimeframe() == GoalTimeframe.WEEKLY) {
                start = end.minusDays(6);
            } else {
                start = end.minusMonths(1).plusDays(1);
            }

            WorkoutType type = goal.getWorkoutType();
            int actualValue;

            // delegate calculations to ProgressService
            if (goal.getMetric() == GoalMetric.WORKOUT_COUNT) {
                actualValue = progressService
                        .totalWorkoutsByTypeAndDateRange(type, start, end);

            } else if (goal.getMetric() == GoalMetric.TOTAL_DURATION) {
                actualValue = progressService
                        .totalDurationByTypeAndDateRange(type, start, end);

            } else { // CALORIES
                actualValue = progressService
                        .totalCaloriesByTypeAndDateRange(type, start, end);
            }

            return actualValue >= goal.getTargetValue();
        }
}
