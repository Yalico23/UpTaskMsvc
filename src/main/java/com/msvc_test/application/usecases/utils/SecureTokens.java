package com.msvc_test.application.usecases.utils;

import java.security.SecureRandom;

public class SecureTokens {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String newToken(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Invalid length");
        }

        StringBuilder token = new StringBuilder(length);

        for(int i=0; i<length; i++){
            token.append(SECURE_RANDOM.nextInt(10)); // Genera un número aleatorio entre 0 y 9
        }

        return token.toString();
    }
}
