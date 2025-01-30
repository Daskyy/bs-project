package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import jakarta.persistence.EntityManager;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;

public class UserQueryResolver implements GraphQLQueryResolver {

    public UserGQL user(String id){
        UserMapperGQL mapperGQl= new UserMapperGQL();
        UserMapper EntityMapper= new UserMapper();
        try(PersistenceManager persistenceManager = PersistenceManager.create())
        {
            EntityManager em= persistenceManager.getEntityManager();
            UserHandler uh= new UserHandler(em);
            UserEntity ue=  uh.findById(Long.parseLong(id)).orElse(null);
            if(ue==null) return null; //todo 404 not found
            User u= EntityMapper.toModel(ue);
            return mapperGQl.toModelGQL(u);
        }
    }
}
