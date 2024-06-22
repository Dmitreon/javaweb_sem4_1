package com.example.javaweb_sem4_1.util.validator;

public class FieldValidator {

    private FieldValidator() {
    }
    public static boolean isValidField(String field) {
        return "username".equals(field) || "email".equals(field) || "id".equals(field);
    }
}
