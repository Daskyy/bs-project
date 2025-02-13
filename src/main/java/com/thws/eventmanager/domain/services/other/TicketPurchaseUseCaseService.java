package com.thws.eventmanager.domain.services.other;

import com.stripe.model.Refund;
import com.thws.eventmanager.domain.models.*;
import com.thws.eventmanager.domain.port.in.TicketPurchaseUseCaseInterface;
import com.thws.eventmanager.domain.services.models.EventService;
import com.thws.eventmanager.domain.services.models.PaymentService;
import com.thws.eventmanager.domain.services.models.TicketService;
import com.thws.eventmanager.infrastructure.components.paymentgateway.StripePaymentService;
import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.PaymentMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.TicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketPurchaseUseCaseService implements TicketPurchaseUseCaseInterface {
    PaymentUseCaseService stripe = new PaymentUseCaseService(new StripePaymentService());
    private static final Logger log = LoggerFactory.getLogger(TicketPurchaseUseCaseService.class);
    private final TicketService ticketService = new TicketService();
    private final PaymentMapper paymentMapper = new PaymentMapper();
    private final TicketMapper ticketMapper = new TicketMapper();
    private final PaymentService paymentService = new PaymentService();

    @Override
    public Payment makePayment(User user, Event event, int ticketAmount, String paymentMethodId, String voucherCode) {
        Payment payment = new Payment(null, event.getTicketPrice() * ticketAmount);

        /*
            boolean createdPayment = stripe.createOpenPayment(payment);
            Pass payment to user via frontend or wherever
         */
        if(event.getTicketsSold() + ticketAmount > event.getTicketCount()) {
            payment.setStatus(Status.FAILED);
            throw new IllegalArgumentException("Not enough tickets left");


        }

        payment.setPaymentMethodId("pm_card_visa"); // THIS LINE IS ONLY FOR SHOWCASING. THIS SHOULD INSTEAD BE FILLED BY STRIPE UI AUTOMATICALLY

        stripe.processPayment(payment, voucherCode);

        //PaymentService paymentService = new PaymentService();
        //paymentService.createPayment(payment);
        return payment;
    }

    @Override
    public Payment makePayment(User user, Event event, int ticketAmount, String paymentMethodId) {
        return makePayment(user, event, ticketAmount, paymentMethodId, null);
    }

    @Override
    public TicketEntity createTicket(User user, Event event, Payment payment) {
        Ticket ticket = new Ticket(event, user, payment);
        ticket.setPayment(payment);
        if(payment.getStatus() == Status.COMPLETED) {
            EventService eventService = new EventService();
            event.setTicketsSold(event.getTicketsSold() + 1);
            eventService.createEvent(event);
            return ticketService.createTicket(ticket);
        }
        return null;
    }

    public boolean refundTicket(Ticket ticket) {
        if (ticket.getPayment().getStatus() != Status.COMPLETED) {
            throw new IllegalStateException("Only completed payments can be refunded.");
        }

        Payment payment = ticket.getPayment();
        long refundAmount = payment.getAmount(); // Refund full amount
        Refund refund = stripe.refundPayment(payment, refundAmount);

        if (refund != null && "succeeded".equals(refund.getStatus())) {
            ticket.getEvent().setTicketsSold(ticket.getEvent().getTicketsSold() - 1); // Reduce ticket count
            log.info("Ticket for event {} refunded successfully", ticket.getEvent().getName());

            EventService eventService = new EventService();
            paymentService.createPayment(payment);
            ticketService.deleteTicket(ticket);
            eventService.createEvent(ticket.getEvent());
            return true;
        }
        return false;
    }

}
