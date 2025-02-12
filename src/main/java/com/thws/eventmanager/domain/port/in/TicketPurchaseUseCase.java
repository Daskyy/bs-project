package com.thws.eventmanager.domain.port.in;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.User;

public interface TicketPurchaseUseCase {
    // Method to handle the full process of ticket purchase (reservation + payment)
    boolean purchaseTicket(User user, Event event, int ticketAmount, String paymentMethodId);
}
