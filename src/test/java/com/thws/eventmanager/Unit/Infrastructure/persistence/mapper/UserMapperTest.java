package com.thws.eventmanager.Unit.Infrastructure.persistence.mapper;

import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {
    private final UserMapper mapper = new UserMapper();

    @Test
    void toModelTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Alice");
        userEntity.setEmail("alice@example.com");
        userEntity.setPassword("securepass");
        userEntity.setPermission(Permission.CUSTOMER);

        User user = mapper.toModel(userEntity);

        assertEquals(1L, user.getId());
        assertEquals("Alice", user.getName());
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("securepass", user.getPassword());
        assertEquals(Permission.CUSTOMER, user.getPermission());
    }
    @Test
    void toEntityTest() {
        User user = new User();
        user.setId(1L);
        user.setName("Bob");
        user.setEmail("bob@example.com");
        user.setPassword("mypassword");
        user.setPermission(Permission.ARTIST);

        UserEntity entity = mapper.toEntity(user);

        assertEquals("Bob", entity.getName());
        assertEquals("bob@example.com", entity.getEmail());
        assertEquals("mypassword", entity.getPassword());
        assertEquals(Permission.ARTIST, entity.getPermission());
    }
}
