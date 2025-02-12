package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.UserInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PermissionGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.UserGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.UserInputMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputMapperTest {
    private final UserInputMapper mapper = new UserInputMapper();

    @Test
    void toModelGQLTest() {
        UserInput userInput = new UserInput();
        userInput.setName("John Doe");
        userInput.setEmail("john.doe@example.com");
        userInput.setPassword("securePassword123");
        userInput.setPermission(PermissionGQL.STAFF);

        UserGQL userGQL = mapper.toModelGQL(userInput);

        assertNotNull(userGQL);
        assertEquals("John Doe", userGQL.getName());
        assertEquals("john.doe@example.com", userGQL.getEmail());
        assertEquals("securePassword123", userGQL.getPassword());
        assertEquals(PermissionGQL.STAFF, userGQL.getPermission());
    }
}