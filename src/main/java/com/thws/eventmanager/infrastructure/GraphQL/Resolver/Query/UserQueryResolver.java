package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import org.slf4j.*;

public class UserQueryResolver implements GraphQLQueryResolver {
    private static final Logger logger = LoggerFactory.getLogger(UserQueryResolver.class);

    public UserGQL user(String id){
        UserMapperGQL mapperGQl= new UserMapperGQL();
        UserMapper EntityMapper= new UserMapper();
        try(UserHandler uh= new UserHandler())
        {
            UserEntity ue =  uh.findById(Long.parseLong(id)).orElse(null);
            if(ue==null) return null; //todo 404 not found
            User u= EntityMapper.toModel(ue);
            return mapperGQl.toModelGQL(u);
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage(), e); // ✅ Proper logging
            return null;
        }
    }
}
