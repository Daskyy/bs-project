package com.thws.eventmanager.domain.usecases;

import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.port.in.TicketServiceInterface;
import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.TicketHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.PaymentEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.PaymentMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.TicketMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import jakarta.persistence.EntityManager;

public class TicketService implements TicketServiceInterface {
    private final TicketMapper ticketMapper = new TicketMapper();
    private final EventMapper eventMapper = new EventMapper();
    private final UserMapper userMapper = new UserMapper();
    private final PaymentMapper paymentMapper = new PaymentMapper();

    @Override
    public TicketEntity createTicket(Ticket ticket) {
        if (!validateTicket(ticket)) {
            throw new RuntimeException("Ticket is not valid");
        }

        try (TicketHandler ticketHandler = new TicketHandler()) {
            TicketEntity ticketEntity = ticketMapper.toEntity(ticket);
            return ticketHandler.save(ticketEntity); // Cascade handles everything
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating ticket");
        }
    }



    @Override
    public boolean validateTicket(Ticket ticket) {
        Status status = ticket.getPayment().getStatus();
        return status == Status.COMPLETED;
    }
}
