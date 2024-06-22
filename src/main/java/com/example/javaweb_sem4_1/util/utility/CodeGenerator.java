package com.example.javaweb_sem4_1.util.utility;

import java.util.Random;

public class CodeGenerator {
    private static final Random random = new Random();

    public static int generate() {
        return 100000 + random.nextInt(900000);
    }
}
