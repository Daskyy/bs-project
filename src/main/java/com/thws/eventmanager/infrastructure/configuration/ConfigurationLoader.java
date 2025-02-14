package com.thws.eventmanager.infrastructure.configuration;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

public class ConfigurationLoader {
    private static final Properties properties = new Properties();
    private static final String SWIPER_NO_SWIPING = "BS2024eventManagerjfuri4eventMan"; // Same key as Encrypt class

    static {
        try (InputStream input = ConfigurationLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("application.properties not found");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getStripeApiKey(String key) {
        String hashedKey = SwiperNoSwiping.hash(key);
        String encryptedValue = properties.getProperty(hashedKey);

        if (encryptedValue != null && encryptedValue.startsWith("ENC(") && encryptedValue.endsWith(")")) {
            encryptedValue = encryptedValue.substring(4, encryptedValue.length() - 1);
            return decrypt(encryptedValue);
        }
        return null;
    }

    private static String decrypt(String encryptedText) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SWIPER_NO_SWIPING.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting", e);
        }
    }
}


