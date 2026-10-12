package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student() {
        super();
        this.active = true;
    }

    // constructor with email
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true; // new student is active by default
    }

    // constructor without email (constructor overloading)
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, "N/A");
        this.batch = batch;
        this.active = true;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " [" + batch + "]";
    }

    @Override
    public String toString() {
        String status;
        if (active) {
            status = "Active";
        } else {
            status = "Inactive";
        }
        return "ID: " + getId() + " | Name: " + getDisplayName() + " | Email: " + getEmail() + " | Status: " + status;
    }
}
