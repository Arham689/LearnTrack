package com.airtribe.learntrack.service;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentService {

    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    // need these to check if student and course exist
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) throws EntityNotFoundException, InvalidInputException {
        // this will throw exception if not found
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Student is not active, cannot enroll");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Course is not active, cannot enroll");
        }
        if (isAlreadyEnrolled(studentId, courseId)) {
            throw new InvalidInputException("Student is already enrolled in this course");
        }

        Enrollment e = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId, courseId);
        enrollments.add(e);
        return e;
    }

    // default access - only used inside service package
    boolean isAlreadyEnrolled(int studentId, int courseId) {
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId && e.getCourseId() == courseId && e.getStatus().equals(Enrollment.ACTIVE)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Enrollment> getEnrollmentsForStudent(int studentId) throws EntityNotFoundException {
        studentService.findStudentById(studentId); // just to check student exists

        ArrayList<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    public ArrayList<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    public Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new EntityNotFoundException("Enrollment with id " + id + " not found");
    }

    public void updateStatus(int enrollmentId, String newStatus) throws EntityNotFoundException, InvalidInputException {
        Enrollment e = findEnrollmentById(enrollmentId);

        if (!e.getStatus().equals("ACTIVE")) {
            throw new InvalidInputException("Only ACTIVE enrollments can be updated. Current status is " + e.getStatus());
        }
        e.setStatus(newStatus);
    }
}
