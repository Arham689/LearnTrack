package com.airtribe.learntrack.entity;

// Trainer is not used much right now, added it to show inheritance
public class Trainer extends Person {

    private String expertise;

    public Trainer(int id, String firstName, String lastName, String email, String expertise) {
        super(id, firstName, lastName, email);
        this.expertise = expertise;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    @Override
    public String getDisplayName() {
        return "Trainer " + super.getDisplayName() + " (" + expertise + ")";
    }
}
