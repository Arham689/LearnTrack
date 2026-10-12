package com.airtribe.learntrack.service;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

public class CourseService {

    private ArrayList<Course> courses = new ArrayList<>();

    public Course addCourse(String name, String desc, int weeks) throws InvalidInputException {
        if (InputValidator.isEmpty(name)) {
            throw new InvalidInputException("Course name cannot be empty");
        }
        if (!InputValidator.isPositive(weeks)) {
            throw new InvalidInputException("Duration should be more than 0 weeks");
        }
        Course c = new Course(IdGenerator.getNextCourseId(), name, desc, weeks);
        courses.add(c);
        return c;
    }

    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new EntityNotFoundException("Course with id " + id + " not found");
    }

    public void activateCourse(int id) throws EntityNotFoundException {
        Course c = findCourseById(id);
        c.setActive(true);
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {
        Course c = findCourseById(id);
        c.setActive(false);
    }
}
