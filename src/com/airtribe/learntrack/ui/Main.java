package com.airtribe.learntrack.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Trainer;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static StudentService studentService = new StudentService();
    static CourseService courseService = new CourseService();
    static EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        loadSampleData();

        System.out.println("===================================");
        System.out.println("   Welcome to LearnTrack");
        System.out.println("===================================");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("------ MAIN MENU ------");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("0. Exit");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    courseMenu();
                    break;
                case 3:
                    enrollmentMenu();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using LearnTrack. Bye!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        sc.close();
    }

    // ================= STUDENT MENU =================
    static void studentMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println("--- Student Management ---");
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Deactivate student");
            System.out.println("0. Back");
            choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        int id = readInt("Enter student id: ");
                        Student s = studentService.findStudentById(id);
                        System.out.println(s);
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        int sid = readInt("Enter student id to deactivate: ");
                        studentService.deactivateStudent(sid);
                        System.out.println("Student deactivated.");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 0);
    }

    static void addStudent() throws InvalidInputException {
        System.out.print("First name: ");
        String fname = sc.nextLine();
        System.out.print("Last name: ");
        String lname = sc.nextLine();
        System.out.print("Batch: ");
        String batch = sc.nextLine();
        System.out.print("Email (press enter to skip): ");
        String email = sc.nextLine();

        Student s;
        if (email.trim().isEmpty()) {
            s = studentService.addStudent(fname, lname, batch);
        } else {
            s = studentService.addStudent(fname, lname, email, batch);
        }
        System.out.println("Student added successfully with id " + s.getId());
    }

    static void viewAllStudents() {
        ArrayList<Student> list = studentService.listStudents();
        if (list.size() == 0) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("All Students:");
        for (Student s : list) {
            System.out.println(s);
        }
    }

    static void updateStudent() throws EntityNotFoundException, InvalidInputException {
        int id = readInt("Enter student id: ");
        Student s = studentService.findStudentById(id);
        System.out.println("Current: " + s);

        System.out.print("New email (enter to keep same): ");
        String email = sc.nextLine();
        System.out.print("New batch (enter to keep same): ");
        String batch = sc.nextLine();

        studentService.updateStudent(id, email, batch);
        System.out.println("Student updated.");
    }

    // ================= COURSE MENU =================
    static void courseMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println("--- Course Management ---");
            System.out.println("1. Add new course");
            System.out.println("2. View all courses");
            System.out.println("3. Activate course");
            System.out.println("4. Deactivate course");
            System.out.println("0. Back");
            choice = readInt("Enter choice: ");

            try {
                if (choice == 1) {
                    System.out.print("Course name: ");
                    String name = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    int weeks = readInt("Duration in weeks: ");
                    Course c = courseService.addCourse(name, desc, weeks);
                    System.out.println("Course added with id " + c.getId());
                } else if (choice == 2) {
                    ArrayList<Course> courses = courseService.getAllCourses();
                    if (courses.isEmpty()) {
                        System.out.println("No courses found.");
                    } else {
                        for (Course c : courses) {
                            System.out.println(c);
                        }
                    }
                } else if (choice == 3) {
                    int id = readInt("Enter course id: ");
                    courseService.activateCourse(id);
                    System.out.println("Course activated.");
                } else if (choice == 4) {
                    int id = readInt("Enter course id: ");
                    courseService.deactivateCourse(id);
                    System.out.println("Course deactivated.");
                } else if (choice != 0) {
                    System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 0);
    }

    // ================= ENROLLMENT MENU =================
    static void enrollmentMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println("--- Enrollment Management ---");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View enrollments of a student");
            System.out.println("3. Mark enrollment as COMPLETED");
            System.out.println("4. Mark enrollment as CANCELLED");
            System.out.println("5. View all enrollments");
            System.out.println("0. Back");
            choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1:
                        int studentId = readInt("Student id: ");
                        int courseId = readInt("Course id: ");
                        Enrollment e = enrollmentService.enrollStudent(studentId, courseId);
                        System.out.println("Enrolled successfully. Enrollment id: " + e.getId());
                        break;
                    case 2:
                        showStudentEnrollments();
                        break;
                    case 3:
                        int eid = readInt("Enrollment id: ");
                        enrollmentService.updateStatus(eid, Enrollment.COMPLETED);
                        System.out.println("Marked as completed.");
                        break;
                    case 4:
                        int eid2 = readInt("Enrollment id: ");
                        enrollmentService.updateStatus(eid2, Enrollment.CANCELLED);
                        System.out.println("Marked as cancelled.");
                        break;
                    case 5:
                        ArrayList<Enrollment> all = enrollmentService.getAllEnrollments();
                        if (all.isEmpty()) {
                            System.out.println("No enrollments yet.");
                        }
                        for (Enrollment en : all) {
                            System.out.println(en);
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (InvalidInputException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } while (choice != 0);
    }

    static void showStudentEnrollments() throws EntityNotFoundException {
        int studentId = readInt("Student id: ");
        ArrayList<Enrollment> list = enrollmentService.getEnrollmentsForStudent(studentId);
        if (list.isEmpty()) {
            System.out.println("This student has no enrollments.");
            return;
        }
        for (Enrollment e : list) {
            // show course name also, looks better
            String courseName;
            try {
                courseName = courseService.findCourseById(e.getCourseId()).getCourseName();
            } catch (EntityNotFoundException ex) {
                courseName = "Unknown";
            }
            System.out.println(e + " | Course: " + courseName);
        }
    }

    // reads a number from user, keeps asking until valid number is given
    static int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    // some dummy data so we dont have to type everything again and again while testing
    static void loadSampleData() {
        try {
            studentService.addStudent("Rahul", "Sharma", "rahul@gmail.com", "Batch-1");
            studentService.addStudent("Priya", "Verma", "Batch-2");
            courseService.addCourse("Core Java", "Java basics and OOP", 8);
            courseService.addCourse("Spring Boot", "Building REST APIs", 6);
        } catch (InvalidInputException e) {
            System.out.println("Could not load sample data: " + e.getMessage());
        }

        // trainer is just created to show inheritance + polymorphism
        Trainer t = new Trainer(IdGenerator.getNextTrainerId(), "Amit", "Kumar", "amit@airtribe.com", "Java");
        System.out.println("Trainer for this cohort: " + t.getDisplayName());
    }
}
