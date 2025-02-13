package com.thws.eventmanager.debug;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.services.usecases.TicketPurchaseUseCaseService;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;

public class PurchaseTicket {
    public static void main(String[] args) {
        try(UserHandler uh= new UserHandler(); EventHandler eh = new EventHandler()){
            UserMapper userMapper = new UserMapper();
            EventMapper eventMapper = new EventMapper();

            UserEntity ue = uh.findById(1L).get();
            User user = userMapper.toModel(ue);

            EventEntity ee = eh.findById(1L).get();
            Event event = eventMapper.toModel(ee);

            TicketPurchaseUseCaseService ticketPurchaseUseCaseService = new TicketPurchaseUseCaseService();


            Payment payment = ticketPurchaseUseCaseService.makePayment(userMapper.toModel(ue), eventMapper.toModel(ee), 1, null, "BRAUN10");
            ticketPurchaseUseCaseService.createTicket(user, event, payment);
        }
    }
}
