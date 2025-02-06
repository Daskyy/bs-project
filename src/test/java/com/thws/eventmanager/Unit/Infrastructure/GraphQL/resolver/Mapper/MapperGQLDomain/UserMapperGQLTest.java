package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PermissionGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.UserMapperGQL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperGQLTest {
    private final UserMapperGQL mapper = new UserMapperGQL();

    @Test
    void testToModel() {
        UserGQL userGQL = new UserGQL();
        userGQL.setName("John Doe");
        userGQL.setEmail("johndoe@example.com");
        userGQL.setPassword("password123");
        userGQL.setPermission(PermissionGQL.STAFF);

        User user = mapper.toModel(userGQL);

        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals("johndoe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(Permission.STAFF, user.getPermission());
    }

    @Test
    void testToModelNull() {
        User user = mapper.toModel(null);
        assertNull(user);
    }

    @Test
    void testToModelGQL() {
        User user = new User();
        user.setId(123L);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password123");
        user.setPermission(Permission.STAFF);

        UserGQL userGQL = mapper.toModelGQL(user);

        assertNotNull(userGQL);
        assertEquals("123", userGQL.getId());
        assertEquals("John Doe", userGQL.getName());
        assertEquals("johndoe@example.com", userGQL.getEmail());
        assertEquals("password123", userGQL.getPassword());
        assertEquals(PermissionGQL.STAFF, userGQL.getPermission());
    }

    @Test
    void testToModelGQLNull() {
        UserGQL userGQL = mapper.toModelGQL(null);
        assertNull(userGQL);
    }
}