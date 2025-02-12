package com.thws.eventmanager.database;

import com.thws.eventmanager.domain.usecases.TicketPurchaseUseCaseService;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.TicketHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.TicketMapper;

public class RefundTicket {
    private static final TicketMapper ticketMapper = new TicketMapper();
    public static void main(String[] args) {
        try(TicketHandler th = new TicketHandler()) {
            TicketEntity ticketEntity = th.findById(5L).get();
            TicketPurchaseUseCaseService tpucs = new TicketPurchaseUseCaseService();
            tpucs.refundTicket(ticketMapper.toModel(ticketEntity));
        }
    }
}
