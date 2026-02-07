package com.project.fitnesstracker;

public class Goal {

    private final WorkoutType workoutType;
    private final GoalMetric metric;
    private final int targetValue;
    private final String description;
    private final GoalTimeframe timeframe;

    public Goal(
            String description,
            WorkoutType workoutType,
            GoalMetric metric,
            GoalTimeframe timeframe,
            int targetValue
    ) {
        if (workoutType == null || metric == null || timeframe == null) {
            throw new IllegalArgumentException("Goal fields cannot be empty!");
        }

        if (targetValue <= 0) {
            throw new IllegalArgumentException("Target value must be greater than zero!");
        }

        this.description = description;
        this.workoutType = workoutType;
        this.metric = metric;
        this.timeframe = timeframe;
        this.targetValue = targetValue;
    }

    public String getDescription() {
        return description;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public GoalMetric getMetric() {
        return metric;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public GoalTimeframe getTimeframe() {
        return timeframe;
    }
}
