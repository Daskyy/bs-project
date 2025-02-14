package com.thws.eventmanager.Integration.Operations;

import com.thws.eventmanager.infrastructure.GraphQL.Models.TicketGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PaymentGQL;

public class TicketOperation extends AbstractAPIOperation {

    public TicketGQL purchaseTicket(String userId, String eventId, int ticketAmount, String paymentMethodId, String voucher) throws Exception {
        String mutation = String.format(
            "mutation purchaseTicket { " +
            "  purchaseTicket(userId: \"%s\", eventId: \"%s\", ticketamount: %d, paymentmethodId: \"%s\", voucher: \"%s\") { " +
            "    id " +
            "    event { id } " +
            "    user { id } " +
            "    payment { id status } " +
            "  }" +
            "}",
            userId, eventId, ticketAmount, paymentMethodId, voucher == null ? "" : voucher);
        return executeQuery(mutation, "purchaseTicket", TicketGQL.class);
    }

    public PaymentGQL refundTicket(String ticketId) throws Exception {
        String mutation = String.format(
            "mutation refundTicket { " +
            "  refundTicket(ticketId: \"%s\") { " +
            "    id " +
            "    paymentMethodId " +
            "    amount " +
            "    status " +
            "    paymentIntentId " +
            "  }" +
            "}",
            ticketId);
        return executeQuery(mutation, "refundTicket", PaymentGQL.class);
    }

} 