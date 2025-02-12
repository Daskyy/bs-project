package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.port.in.TicketPurchaseUseCase;
import com.thws.eventmanager.domain.port.out.PaymentService;
import com.thws.eventmanager.infrastructure.components.paymentgateway.StripePaymentService;

public class TicketPurchaseUseCaseService implements TicketPurchaseUseCase {
    PaymentService stripe = new StripePaymentService();
    @Override
    public boolean purchaseTicket(User user, Event event, int ticketAmount, String paymentMethodId) {
        Payment payment = new Payment(null, event.getTicketPrice() * ticketAmount);
        boolean createdPayment = stripe.createOpenPayment(payment);

        /*

            Pass payment to user via frontend or wherever

         */

        payment.setPaymentMethodId("pm_card_visa"); // THIS LINE IS ONLY FOR SHOWCASING. THIS SHOULD INSTEAD BE FILLED BY STRIPE UI AUTOMATICALLY
        return stripe.processPayment(payment);
    }
}
