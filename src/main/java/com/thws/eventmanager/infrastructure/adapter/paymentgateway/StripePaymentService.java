package com.thws.eventmanager.infrastructure.adapter.paymentgateway;

import com.stripe.exception.*;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.thws.eventmanager.domain.service.PaymentService;
import com.thws.eventmanager.domain.models.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentService implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(StripePaymentService.class);
    private static final String EUR_CURRENCY = "eur";

    @Override
    public boolean processPayment(Payment payment) {
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

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            log.info("Payment processed successfully. PaymentIntent ID: {}", paymentIntent.getId());
            return "succeeded".equals(paymentIntent.getStatus());
        });
    }

    @Override
    public boolean createOpenPayment(Payment payment) {
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
            return paymentIntent.getStatus() != null;
        });
    }

    @Override
    public boolean createFailedPayment(Payment payment) {
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
            return "requires_payment_method".equals(paymentIntent.getStatus());
        } catch (InvalidRequestException e) {
            log.error("Invalid request to Stripe API: {}", e.getMessage());
            return false;
        } catch (StripeException e) {
            log.error("Stripe error occurred: {}", e.getMessage());
            return false;
        }
    }

    private boolean executeStripeOperation(SupplierWithStripeException<Boolean> operation) {
        try {
            return operation.get();
        } catch (CardException e) {
            log.error("Card payment failed. Code: {}, Message: {}, Decline Code: {}",
                    e.getCode(),
                    e.getMessage(),
                    e.getDeclineCode());
        } catch (AuthenticationException e) {
            log.error("Authentication with Stripe failed: {}", e.getMessage());
        } catch (ApiConnectionException e) {
            log.error("Failed to connect to Stripe API: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during Stripe operation", e);
        }
        return false;
    }

    @FunctionalInterface
    private interface SupplierWithStripeException<T> {
        T get() throws StripeException;
    }
}