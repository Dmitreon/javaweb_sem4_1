package com.example.javaweb_sem4_1.util.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class UserValidator {

    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MAX_USERNAME_LENGTH = 10;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]{" + MIN_USERNAME_LENGTH + "," + MAX_USERNAME_LENGTH + "}$");
    private static final int MIN_PASSWORD_LENGTH = 3;
    private static final int MAX_PASSWORD_LENGTH = 10;
    private static final int MAX_EMAIL_LENGTH = 30;

    private static final Set<String> BANNED_USERNAMES = new HashSet<>(Arrays.asList(
            "blinov", "Igor"
    ));

    private static final Set<String> BANNED_EMAILS = new HashSet<>(Arrays.asList(
            "n.gashkin@gmail.com", "blinov@gmail.com"
    ));

    public static boolean isValidUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches() && !BANNED_USERNAMES.contains(username.toLowerCase());
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches() &&
                !BANNED_EMAILS.contains(email.toLowerCase()) &&
                email.length() <= MAX_EMAIL_LENGTH;
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH && password.length() <= MAX_PASSWORD_LENGTH;
    }
}
