package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Ticket;
import com.thws.eventmanager.infrastructure.GraphQL.Models.TicketGQL;

public class TicketMapperGQL extends Mapper<Ticket, TicketGQL> {
    UserMapperGQL userMapperGQL = new UserMapperGQL();
    EventMapperGQL eventMapperGQL = new EventMapperGQL();
    PaymentMapperGQL paymentMapperGQL = new PaymentMapperGQL();

    public Ticket toModel(TicketGQL gql){
        if (gql == null) return null;
        Ticket domain = new Ticket();
        domain.setEvent(eventMapperGQL.toModel(gql.getEvent()));
        domain.setUser(userMapperGQL.toModel(gql.getUser()));
        domain.setPayment(paymentMapperGQL.toModel(gql.getPayment()));
        if(gql.getId()!=null) domain.setId(Long.parseLong(gql.getId()));
        return domain;
    }

    public TicketGQL toModelGQL(Ticket domain){
        if (domain == null) return null;
        TicketGQL gql = new TicketGQL();
        gql.setEvent(eventMapperGQL.toModelGQL(domain.getEvent()));
        gql.setUser(userMapperGQL.toModelGQL(domain.getUser()));
        gql.setPayment(paymentMapperGQL.toModelGQL(domain.getPayment()));
        gql.setId(String.valueOf(domain.getId()));
        if(domain.getId()!=-1) gql.setId(String.valueOf(domain.getId()));
        return gql;
    }



}
