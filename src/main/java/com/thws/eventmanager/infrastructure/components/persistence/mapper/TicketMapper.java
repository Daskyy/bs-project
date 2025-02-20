package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;

public class TicketMapper extends Mapper<Ticket, TicketEntity> {
    EventMapper eventMapper = new EventMapper();
    UserMapper userMapper = new UserMapper();
    PaymentMapper paymentMapper = new PaymentMapper();

    @Override
    public Ticket toModel(TicketEntity entity){
        Ticket ticket = new Ticket();

        ticket.setEvent(eventMapper.toModel(entity.getEvent()));
        ticket.setUser(userMapper.toModel(entity.getUser()));
        ticket.setPayment(paymentMapper.toModel(entity.getPayment()));
        ticket.setId(entity.getId());

        return ticket;
    }

    @Override
    public TicketEntity toEntity(Ticket ticket) {
        TicketEntity entity = new TicketEntity();

        entity.setEvent(eventMapper.toEntity(ticket.getEvent()));
        entity.setUser(userMapper.toEntity(ticket.getUser()));
        entity.setPayment(paymentMapper.toEntity(ticket.getPayment()));
        if(ticket.getId() != -1) {
            entity.setId(ticket.getId());
        }

        return entity;
    }
}
