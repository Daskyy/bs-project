package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mutation;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.usecases.EventService;
import com.thws.eventmanager.domain.usecases.TicketPurchaseUseCaseService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.EventInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.TicketGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EventMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.TicketMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.EventInputMapper;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.UserInputMapper;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.EventHandler;
import graphql.kickstart.tools.GraphQLMutationResolver;

public class PaymentResolver implements GraphQLMutationResolver {
    UserMapperGQL userMapperGQL = new UserMapperGQL();
    UserInputMapper userInputMapper = new UserInputMapper();
    EventMapperGQL eventMapperGQL = new EventMapperGQL();
    EventInputMapper eventInputMapper = new EventInputMapper();
    TicketMapperGQL ticketMapperGQL = new TicketMapperGQL();



    public TicketGQL purchaseTicket(UserInput user, EventInput event, int ticketamount, String paymentmethodId, String voucher){


        TicketPurchaseUseCaseService ticketPurchaseUseCaseService = new TicketPurchaseUseCaseService();
        User u= userMapperGQL.toModel(userInputMapper.toModelGQL(user));
        Event e= eventMapperGQL.toModel(eventInputMapper.toModelGQL(event));
        Payment p= ticketPurchaseUseCaseService.makePayment(u, e, ticketamount, paymentmethodId, voucher);
        Ticket t= ticketPurchaseUseCaseService.createTicket(u, e,p);

        TicketGQL tgql= ticketMapperGQL.toModelGQL(t);
        return tgql;
    }
}
