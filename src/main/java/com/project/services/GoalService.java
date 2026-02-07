package com.project.services;

import com.project.fitnesstracker.Goal;

public interface GoalService {

    void registerGoal(Goal goal);

    boolean isGoalMet(Goal goal);
}
