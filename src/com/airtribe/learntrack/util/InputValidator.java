package com.airtribe.learntrack.util;

public class InputValidator {

    public static boolean isEmpty(String value) {
        if (value == null || value.trim().length() == 0) {
            return true;
        }
        return false;
    }

    // simple check, not a perfect email validation
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }

    public static boolean isPositive(int num) {
        return num > 0;
    }
}
