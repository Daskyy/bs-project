package com.thws.eventmanager.domain.usecases;

import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.thws.eventmanager.domain.port.in.PaymentUseCaseInterface;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.port.out.StripeServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentUseCaseService implements PaymentUseCaseInterface {
    private final StripeServiceInterface paymentService;
    private static final Logger log = LoggerFactory.getLogger(PaymentUseCaseService.class);
    private final VoucherService voucherService;

    public PaymentUseCaseService(StripeServiceInterface paymentService) {
        this.paymentService = paymentService;
        this.voucherService = new VoucherService();
    }

    @Override
    public PaymentIntent processPayment(Payment payment, String voucherCode) {
        log.info("Processing payment with voucher code: {}", voucherCode);
        if (voucherCode != null) {
            System.out.println("Applying voucher: " + voucherCode);
            long discount = voucherService.applyVoucher(voucherCode, payment.getAmount());
            long finalAmount = payment.getAmount() - discount;

            if (finalAmount <= 0) {
                payment.setStatus(Status.COMPLETED);
                return null; // No Stripe charge needed
            }
            log.info("Discount applied: {}, final amount: {}", discount, finalAmount);

            payment.setAmount(finalAmount);
        }

        log.info("Final amount sent to Stripe: {}", payment.getAmount());

        PaymentIntent paymentIntent = paymentService.processPayment(payment);

        if (paymentIntent != null) {
            payment.setPaymentIntentId(paymentIntent.getId());

            // Handle all potential payment statuses
            switch (paymentIntent.getStatus()) {
                case "succeeded":
                    payment.setStatus(Status.COMPLETED);
                    break;
                case "failed":
                    payment.setStatus(Status.FAILED);
                    log.warn("PaymentIntent ID {} failed.", paymentIntent.getId());
                    break;
                default:
                    payment.setStatus(Status.OPEN);
                    log.warn("Unexpected PaymentIntent status: {}", paymentIntent.getStatus());
            }
        } else {
            log.error("PaymentIntent creation failed (null). Setting status to FAILED.");
            payment.setStatus(Status.FAILED);
        }

        return paymentIntent;
    }

    @Override
    public PaymentIntent processPayment(Payment payment) {
        return processPayment(payment, null);
    }

    @Override
    public PaymentIntent createOpenPayment(Payment payment) {
        PaymentIntent paymentIntent = paymentService.createOpenPayment(payment);
        if (paymentIntent != null) {
            payment.setPaymentIntentId(paymentIntent.getId());
            payment.setStatus(paymentIntent.getStatus().equals("requires_payment_method") ? Status.OPEN : Status.FAILED);
        } else {
            payment.setStatus(Status.FAILED);
        }
        return paymentIntent;
    }

    public PaymentIntent createFailedPayment(Payment payment) {
        PaymentIntent paymentIntent = paymentService.createFailedPayment(payment);

        if (paymentIntent != null) {
            payment.setPaymentIntentId(paymentIntent.getId());
            payment.setStatus(Status.FAILED);
        } else {
            log.error("PaymentIntent creation failed. Setting status to FAILED.");
            payment.setStatus(Status.FAILED);
        }
        return paymentIntent;
    }


    @Override
    public Refund refundPayment(Payment payment, long refundAmount) {
        if (payment.getPaymentIntentId() == null) {
            throw new IllegalArgumentException("Cannot refund a payment without a PaymentIntent ID");
        }

        Refund refund = paymentService.processRefund(payment.getPaymentIntentId(), refundAmount);

        if (refund != null && "succeeded".equals(refund.getStatus())) {
            payment.setStatus(Status.REFUNDED); // Update status
            log.info("Payment {} successfully refunded", payment.getPaymentIntentId());
        } else {
            log.warn("Refund failed for payment {}", payment.getPaymentIntentId());
        }
        return refund;
    }
}
