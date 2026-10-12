package com.airtribe.learntrack.util;

// static counters so every new object gets a unique id
// no need to create object of this class
public class IdGenerator {

    private static int studentIdCounter = 100;
    private static int courseIdCounter = 200;
    private static int enrollmentIdCounter = 1;
    private static int trainerIdCounter = 500;

    public static int getNextStudentId() {
        studentIdCounter++;
        return studentIdCounter;
    }

    public static int getNextCourseId() {
        courseIdCounter++;
        return courseIdCounter;
    }

    public static int getNextEnrollmentId() {
        return enrollmentIdCounter++;
    }

    public static int getNextTrainerId() {
        trainerIdCounter++;
        return trainerIdCounter;
    }
}
