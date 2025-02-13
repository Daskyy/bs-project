package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.services.other.TicketPurchaseUseCaseService;
import com.thws.eventmanager.infrastructure.GraphQL.Models.TicketGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.TicketMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.EventInputMapper;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.UserInputMapper;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.TicketEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.EventMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.TicketMapper;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;

public class PaymentResolver implements GraphQLMutationResolver {
    UserMapperGQL userMapperGQL = new UserMapperGQL();
    UserInputMapper userInputMapper = new UserInputMapper();
    EventMapperGQL eventMapperGQL = new EventMapperGQL();
    EventInputMapper eventInputMapper = new EventInputMapper();
    TicketMapperGQL ticketMapperGQL = new TicketMapperGQL();
    EventMapper eventMapper = new EventMapper();
    UserMapper userMapper = new UserMapper();
    TicketMapper ticketMapper = new TicketMapper();


    public TicketGQL purchaseTicket(String userId, String eventId, int ticketamount, String paymentmethodId, String voucher){
        Event e;
        User u;
        try(EventHandler eventHandler = new EventHandler(); UserHandler userHandler = new UserHandler()) {

            EventEntity loaded = eventHandler.findById(Long.parseLong(eventId)).orElseThrow();
            UserEntity loadedUser = userHandler.findById(Long.parseLong(userId)).orElseThrow();
            e = eventMapper.toModel(loaded);
            u = userMapper.toModel(loadedUser);
        }
        TicketPurchaseUseCaseService ticketPurchaseUseCaseService = new TicketPurchaseUseCaseService();
        Payment p= ticketPurchaseUseCaseService.makePayment(u, e, ticketamount, paymentmethodId, voucher);
        TicketEntity t= ticketPurchaseUseCaseService.createTicket(u, e,p);

        Ticket ticket = ticketMapper.toModel(t);
        TicketGQL tgql = ticketMapperGQL.toModelGQL(ticket);
        return tgql;
    }
}
