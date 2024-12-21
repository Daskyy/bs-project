package com.thws.eventmanager.application.port.in;

public interface TicketPurchaseUseCase {
    boolean purchaseTicket(String userEmail, long eventId, int ticketAmount, String paymentMethodId);
}
