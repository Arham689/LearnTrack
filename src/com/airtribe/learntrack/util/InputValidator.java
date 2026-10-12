package com.airtribe.learntrack.util;

public class InputValidator {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    // simple check, not a perfect email validation
    // needs text before "@" and a "." somewhere after it
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        email = email.trim();
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
        return atIndex > 0
                && atIndex == email.lastIndexOf('@')
                && dotIndex > atIndex + 1
                && dotIndex < email.length() - 1;
    }

    public static boolean isPositive(int num) {
        return num > 0;
    }
}
