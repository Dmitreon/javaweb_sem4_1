package com.example.javaweb_sem4_1.util;

import java.security.SecureRandom;

public class CodeGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static int generate() {
        return secureRandom.nextInt(9000) + 1000;
    }
}
