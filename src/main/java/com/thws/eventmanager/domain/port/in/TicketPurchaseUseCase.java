package com.thws.eventmanager.domain.port.in;

public interface TicketPurchaseUseCase {

    // Method to handle the full process of ticket purchase (reservation + payment)
    boolean purchaseTicket(String userEmail, String eventId, int ticketAmount, String paymentMethodId);
}
