package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.domain.port.in.PaymentServiceInterface;
import com.thws.eventmanager.domain.port.in.TicketPurchaseUseCase;
import com.thws.eventmanager.infrastructure.components.paymentgateway.StripePaymentService;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;

public class TicketPurchaseUseCaseService implements TicketPurchaseUseCase {
    PaymentUseCaseService stripe = new PaymentUseCaseService(new StripePaymentService());
    @Override
    public Payment makePayment(User user, Event event, int ticketAmount, String paymentMethodId) {
        Payment payment = new Payment(null, event.getTicketPrice() * ticketAmount);

        /*
            boolean createdPayment = stripe.createOpenPayment(payment);
            Pass payment to user via frontend or wherever
         */

        payment.setPaymentMethodId("pm_card_visa"); // THIS LINE IS ONLY FOR SHOWCASING. THIS SHOULD INSTEAD BE FILLED BY STRIPE UI AUTOMATICALLY
        boolean out = stripe.processPayment(payment);
        payment.setStatus(out ? Status.COMPLETED : Status.FAILED);

        PaymentService paymentService = new PaymentService();
        paymentService.createPayment(payment);
        return payment;
    }

    @Override
    public Ticket createTicket(User user, Event event, Payment payment) {
        Ticket ticket = new Ticket(event, user, payment);
        ticket.setPayment(payment);
        if(payment.getStatus() == Status.COMPLETED) {
            TicketService ticketService = new TicketService();
            ticketService.createTicket(ticket);
            try(EventHandler eventHandler = new EventHandler()) {
                EventService eventService = new EventService(eventHandler);
                event.setTicketsSold(event.getTicketsSold() + 1);
                eventService.createEvent(event);
            }
        }
        return ticket;
    }
}
