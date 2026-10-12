package com.airtribe.learntrack.service;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

public class StudentService {

    private ArrayList<Student> studentList = new ArrayList<>();

    // add student with email
    public Student addStudent(String firstName, String lastName, String email, String batch) throws InvalidInputException {
        if (InputValidator.isEmpty(firstName) || InputValidator.isEmpty(lastName)) {
            throw new InvalidInputException("First name and last name cannot be empty");
        }
        if (!InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Email is not valid");
        }
        Student s = new Student(IdGenerator.getNextStudentId(), firstName, lastName, email, batch);
        studentList.add(s);
        return s;
    }

    // overloaded - add student without email
    public Student addStudent(String firstName, String lastName, String batch) throws InvalidInputException {
        if (InputValidator.isEmpty(firstName) || InputValidator.isEmpty(lastName)) {
            throw new InvalidInputException("First name and last name cannot be empty");
        }
        Student s = new Student(IdGenerator.getNextStudentId(), firstName, lastName, batch);
        studentList.add(s);
        return s;
    }

    public ArrayList<Student> listStudents() {
        return studentList;
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                return studentList.get(i);
            }
        }
        throw new EntityNotFoundException("Student with id " + id + " not found");
    }

    public void updateStudent(int id, String email, String batch) throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(id);

        // only update the fields which user has entered
        if (!InputValidator.isEmpty(email)) {
            if (!InputValidator.isValidEmail(email)) {
                throw new InvalidInputException("Email is not valid");
            }
            student.setEmail(email);
        }
        if (!InputValidator.isEmpty(batch)) {
            student.setBatch(batch);
        }
    }

    // we dont delete the student, just mark inactive
    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
    }

    // this one actually removes from the list (not used in menu right now)
    public void removeStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        studentList.remove(student);
    }
}
