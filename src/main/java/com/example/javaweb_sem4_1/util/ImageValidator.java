package com.example.javaweb_sem4_1.util;

import jakarta.servlet.http.Part;

public class ImageValidator {
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png", "gif"};

    private ImageValidator() {
        // Закрытый конструктор для предотвращения создания экземпляров
    }

    public static String validateImage(Part filePart) {
        if (!isValidSize(filePart)) {
            return "File size exceeds the limit of 5MB.";
        }
        if (!isValidExtension(filePart)) {
            return "Unsupported file type. Only JPG, JPEG, PNG, GIF files are allowed.";
        }
        return null;
    }

    private static boolean isValidSize(Part filePart) {
        return filePart.getSize() <= MAX_FILE_SIZE;
    }

    private static boolean isValidExtension(Part filePart) {
        String fileName = filePart.getSubmittedFileName();
        String fileExtension = getFileExtension(fileName);
        for (String extension : ALLOWED_EXTENSIONS) {
            if (extension.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }

    private static String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
}
