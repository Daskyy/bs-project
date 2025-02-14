package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;

public interface TicketPurchaseUseCaseInterface {
    Payment makePayment(User user, Event event, int ticketAmount, String paymentMethodId);
    Payment makePayment(User user, Event event, int ticketAmount, String paymentMethodId, String voucherCode);
    TicketEntity createTicket(User user, Event event, Payment payment, int ticketAmount);
}
