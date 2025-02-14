package com.thws.eventmanager.infrastructure.components.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import com.thws.eventmanager.domain.port.out.StripeServiceInterface;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.infrastructure.configuration.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StripePaymentService implements StripeServiceInterface {

    private static final Logger log = LoggerFactory.getLogger(StripePaymentService.class);
    private static final String EUR_CURRENCY = "eur";

    static {
        try {
            String stripeMode = ConfigurationLoader.getProperty("stripe.mode");
            Stripe.apiKey = "test".equalsIgnoreCase(stripeMode)
                    ? ConfigurationLoader.getStripeApiKey("stripe.api-key-test")
                    : ConfigurationLoader.getStripeApiKey("stripe.api-key-live");

            log.info("Stripe initialized in {} mode", stripeMode);
        } catch (Exception ex) {
            log.error("Error initializing Stripe", ex);
        }
    }

    @Override
    public PaymentIntent processPayment(Payment payment) {
        log.info("Processing payment for amount: {} EUR", payment.getAmount() / 100.0);

        return executeStripeOperation(() -> {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(payment.getAmount())
                    .setCurrency(EUR_CURRENCY)
                    .setPaymentMethod(payment.getPaymentMethodId())
                    .setConfirm(true)
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER)
                                    .build()
                    )
                    .build();

            try {
                PaymentIntent paymentIntent = PaymentIntent.create(params);
                log.info("Payment processed. PaymentIntent ID: {}", paymentIntent.getId());
                return paymentIntent;
            } catch (CardException e) {
                log.error("Card payment failed. Code: {}, Message: {}, Decline Code: {}",
                        e.getCode(), e.getMessage(), e.getDeclineCode());

                // 🔹 Simulate a failed PaymentIntent with a fake ID
                PaymentIntent failedIntent = new PaymentIntent();
                failedIntent.setStatus("failed");
                failedIntent.setId("pi_fake_declined");
                return failedIntent;
            }
        });
    }



    @Override
    public PaymentIntent createOpenPayment(Payment payment) {
        log.info("Creating open payment for amount: {} EUR", payment.getAmount() / 100.0);

        return executeStripeOperation(() -> {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(payment.getAmount())
                    .setCurrency(EUR_CURRENCY)
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER)
                                    .build()
                    )
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            log.info("Open payment created successfully. PaymentIntent ID: {}", paymentIntent.getId());
            return paymentIntent;
        });
    }


    @Override
    public PaymentIntent createFailedPayment(Payment payment) {
        log.info("Creating failed payment for amount: {} EUR", payment.getAmount() / 100.0);
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(payment.getAmount())
                    .setCurrency(EUR_CURRENCY)
                    .setPaymentMethod(payment.getPaymentMethodId())
                    .setConfirm(false)
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER)
                                    .build()
                    )
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return paymentIntent;
        } catch (InvalidRequestException e) {
            log.error("Invalid request to Stripe API: {}", e.getMessage());

            PaymentIntent failedIntent = new PaymentIntent();
            failedIntent.setStatus("failed");  // Simulate failure
            return failedIntent;
        } catch (StripeException e) {
            log.error("Stripe error occurred: {}", e.getMessage());

            PaymentIntent failedIntent = new PaymentIntent();
            failedIntent.setStatus("failed");
            return failedIntent;
        }
    }



    @Override
    public Refund processRefund(String paymentIntentId, long refundAmount) {
        log.info("Processing refund for PaymentIntent ID: {} with amount: {} EUR", paymentIntentId, refundAmount / 100.0);

        return executeStripeOperation(() -> {
            RefundCreateParams params = RefundCreateParams.builder()
                    .setPaymentIntent(paymentIntentId) // Attach the original payment
                    .setAmount(refundAmount) // Refund full or partial amount
                    .build();

            Refund refund = Refund.create(params);
            log.info("Refund successful. Refund ID: {}, Status: {}", refund.getId(), refund.getStatus());
            return refund;
        });
    }

    private <T> T executeStripeOperation(SupplierWithStripeException<T> operation) {
        try {
            return operation.get();
        } catch (CardException e) {
            log.error("Card payment failed. Code: {}, Message: {}, Decline Code: {}",
                    e.getCode(), e.getMessage(), e.getDeclineCode());

            // 🔹 Return a failed PaymentIntent instead of null
            PaymentIntent failedPaymentIntent = new PaymentIntent();
            failedPaymentIntent.setStatus("failed"); // Simulate Stripe's failed payment
            return (T) failedPaymentIntent;
        } catch (AuthenticationException e) {
            log.error("Authentication with Stripe failed: {}", e.getMessage());
        } catch (ApiConnectionException e) {
            log.error("Failed to connect to Stripe API: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during Stripe operation", e);
        }
        return null; // If a different error occurs, return null
    }



    @FunctionalInterface
    private interface SupplierWithStripeException<T> {
        T get() throws StripeException;
    }
}