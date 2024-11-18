package com.thws.eventmanager.configuration;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfiguration {
    @Value("${stripe.api-key-test}")
    private String testApiKey;

    @Value("${stripe.api-key-live}")
    private String liveApiKey;

    @Value("${stripe.mode}")
    private String mode;

    @PostConstruct
    public void init() {
        if ("live".equalsIgnoreCase(mode)) {
            Stripe.apiKey = liveApiKey;
        } else {
            Stripe.apiKey = testApiKey;
        }
    }
}
