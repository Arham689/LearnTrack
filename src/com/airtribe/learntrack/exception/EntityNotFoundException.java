package com.airtribe.learntrack.exception;

// thrown when student / course / enrollment is not found by id
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
