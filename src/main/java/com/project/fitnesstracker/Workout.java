package com.project.fitnesstracker;

import java.time.LocalDate;

public class Workout {
    // fields
        private final int id;
        private final LocalDate date;
        private final WorkoutType type;
        private final int duration;
        private final int caloriesBurned;

    // constructor
        public Workout(int id, LocalDate date, WorkoutType type, int duration, int caloriesBurned) {

            if (date == null) {
                throw new IllegalArgumentException("Date cannot be empty!");
            }

            if (type == null) {
                throw new IllegalArgumentException("Workout type cannot be empty!");
            }

            if (duration <= 0) {
                throw new IllegalArgumentException(("Workout duration must be greater than 0!"));
            }

            if (caloriesBurned < 0) {
                throw new IllegalArgumentException("Calories burned cannot be negative");
            }

            this.id = id;
            this.date = date;
            this.type = type;
            this.duration = duration;
            this.caloriesBurned = caloriesBurned;
        }

    // accessors
        public int getId() {
            return id;
        }

        public LocalDate getDate() {
            return date;
        }

        public WorkoutType getType() {
            return type;
        }

        public int getDuration() {
            return duration;
        }

        public int getCaloriesBurned() {
            return caloriesBurned;
        }
}