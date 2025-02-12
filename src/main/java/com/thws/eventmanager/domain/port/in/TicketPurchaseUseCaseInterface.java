package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.User;

public interface TicketPurchaseUseCaseInterface {
    // Method to handle the full process of ticket purchase (reservation + payment)
    Payment makePayment(User user, Event event, int ticketAmount, String paymentMethodId);
    Payment makePayment(User user, Event event, int ticketAmount, String paymentMethodId, String voucherCode);
    Ticket createTicket(User user, Event event, Payment payment);
}
