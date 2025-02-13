package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.services.models.UserService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserCriteriaInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.adapter.UserHandler;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import org.slf4j.*;

import java.util.List;
import java.util.Optional;

public class UserQueryResolver implements GraphQLQueryResolver {
    private static final Logger logger = LoggerFactory.getLogger(UserQueryResolver.class);

    UserService userService = new UserService();
    UserMapper userMapper = new UserMapper();
    UserMapperGQL userMapperGQL = new UserMapperGQL();

    public UserGQL user(String id){
        try(UserHandler uh = new UserHandler())
        {
            Optional<UserEntity> optional = uh.findById(Long.parseLong(id));
            if (optional.isPresent()) {
                UserEntity userEntity = uh.findById(Long.parseLong(id)).get();
                User user = new UserMapper().toModel(userEntity);
                UserGQL userGQL = new UserMapperGQL().toModelGQL(user);
                return userGQL;
            }
            throw new Exception("User with id " + id + " not found");
//            UserEntity ue =  uh.findById(Long.parseLong(id)).orElse(null);
//            if(ue==null) return null; //todo 404 not found
//            User u= userMapper.toModel(ue);
//            return userMapperGQL.toModelGQL(u);
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage(), e);
            return null;
        }
    }

    public List<UserGQL> users(UserCriteriaInput criteria) {
        if (criteria == null) {
            return userService.getAllUsers().stream()
                    .map(userMapper::toModel)
                    .map(userMapperGQL::toModelGQL)
                    .toList();
        }

        List<String> criteriaList = criteria.getCriteria();
        List<Object> valuesList = criteria.getValues();
        if (criteriaList.size() != valuesList.size()) {
            throw new IllegalArgumentException("Criteria and values list must have the same size.");
        } else {
            return userService.getAllUsers(criteriaList, valuesList).stream()
                    .map(userMapper::toModel)
                    .map(userMapperGQL::toModelGQL)
                    .toList();
        }
    }
}
