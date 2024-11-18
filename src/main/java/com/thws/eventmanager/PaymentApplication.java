package com.thws.eventmanager;

import com.thws.eventmanager.application.PaymentUseCase;
import com.thws.eventmanager.domain.entities.Payment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PaymentApplication.class, args);

        PaymentUseCase paymentUseCase = context.getBean(PaymentUseCase.class);

        Payment payment = new Payment("pm_card_visa", 5000); // $50.00

        boolean success = paymentUseCase.executePayment(payment);

        if (success) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed.");
        }
    }
}
