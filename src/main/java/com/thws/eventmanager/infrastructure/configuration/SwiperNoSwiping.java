package com.thws.eventmanager.infrastructure.configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class SwiperNoSwiping {
    static String hash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash).replace("=", "");
        } catch (Exception e) {
            throw new RuntimeException("Error hashing", e);
        }
    }
}
