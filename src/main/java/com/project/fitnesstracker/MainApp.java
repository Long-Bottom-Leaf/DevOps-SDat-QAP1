package com.project.fitnesstracker;

import com.project.services.*;

import java.time.LocalDate;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        // initialize services and scanner
            WorkoutService workoutService = new InMemoryWorkoutService();
            ProgressService progressService = new InMemoryProgressService(workoutService);
            GoalService goalService = new InMemoryGoalService(progressService);

            Scanner input = new Scanner(System.in);
            int choice;

        // menu choice logic
            do {
                printMenu();
                System.out.print("Enter choice: ");
                choice = input.nextInt();

                switch (choice) {
                    case 1 -> logWorkout(input, workoutService);
                    case 2 -> showWorkouts(workoutService);
                    case 3 -> showProgress(progressService);
                    case 4 -> setGoal(input, goalService);
                    case 5 -> checkGoals(goalService);
                    case 0 -> System.out.print("Exiting program...");
                    default -> System.out.println("Invalid Choice!");
                }

            } while (choice != 0);

        input.close();
    }

    // input menu
        private static void printMenu() {
            System.out.println("\n=== Fitness Tracker Menu ===");
            System.out.println("1. Log Workout");
            System.out.println("2. Show All Workouts");
            System.out.println("3. Show Progress");
            System.out.println("4. Set Goal");
            System.out.println("5. Check Goals");
            System.out.println("0. Exit");
        }

    // log workout logic
        private static void logWorkout(Scanner input, WorkoutService workoutService) {
            try {
                System.out.print("Enter Workout ID: ");
                int id = input.nextInt();
                input.nextLine();

                System.out.print("Enter Workout Date (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(input.nextLine());

                System.out.print("Enter Workout Type (RUNNING, CYCLING, SWIMMING, WEIGHTS, YOGA): ");
                WorkoutType type = WorkoutType.valueOf(input.nextLine().toUpperCase());

                System.out.print("Enter Duration (minutes): ");
                int duration = input.nextInt();

                System.out.print("Enter Calories Burned: ");
                int calories = input.nextInt();
                input.nextLine();

                Workout workout = new Workout(id, date, type, duration, calories);
                workoutService.logWorkout(workout);
                System.out.println("Workout logged successfully!");

            } catch (Exception error) {
                System.out.println("Error logging workout: " + error.getMessage());
            }
        }

    // show workouts logic
        private static void showWorkouts(WorkoutService workoutService) {
            System.out.println("\n=== All Workouts ===");
            for (Workout workout : workoutService.getAllWorkouts()) {
                System.out.println(workout.getId() + " | " + workout.getDate() + " | " + workout.getType() + " | " + workout.getDuration() + " min | " + workout.getCaloriesBurned() + " cal");
            }
        }

    // show progress logic
        private static void showProgress(ProgressService progressService) {
            System.out.println("\n=== Progress ===");
            System.out.println("Total Workouts: " + progressService.totalWorkouts());
            System.out.println("Total Duration: " + progressService.totalDuration() + " min");
            System.out.println("Total Calories: " + progressService.totalCalories() + " cal");
        }

    // set goal logic
        private static void setGoal(Scanner input, GoalService goalService) {
            try {
                System.out.print("Enter Goal Description: ");
                String desc = input.nextLine();

                System.out.print("Enter Workout Type (RUNNING, CYCLING, SWIMMING, WEIGHTS, YOGA): ");
                WorkoutType type = WorkoutType.valueOf(input.nextLine().toUpperCase());

                System.out.print("Enter Metric (WORKOUT_COUNT, TOTAL_DURATION, TOTAL_CALORIES): ");
                GoalMetric metric = GoalMetric.valueOf(input.nextLine().toUpperCase());

                System.out.print("Enter Timeframe (WEEKLY, MONTHLY): ");
                GoalTimeframe timeframe = GoalTimeframe.valueOf(input.nextLine().toUpperCase());

                System.out.print("Enter Target Value: ");
                int target = input.nextInt();
                input.nextLine();

                Goal goal = new Goal(desc, type, metric, timeframe, target);
                goalService.registerGoal(goal);

                System.out.println("Goal registered successfully!");

            } catch (Exception error) {
                System.out.println("Error setting goal: " + error.getMessage());
            }
        }

    private static void checkGoals(GoalService goalService) {
        System.out.println("\n=== Goal Status ===");

        for (Goal goal : goalService.getAllGoals()) {
            System.out.println(goal.getDescription() + " | " + (goalService.isGoalMet(goal) ? "Met" : "Not Met"));
        }
    }

}
