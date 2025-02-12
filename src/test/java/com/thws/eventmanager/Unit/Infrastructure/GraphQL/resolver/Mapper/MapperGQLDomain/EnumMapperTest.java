package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Permission;
import com.thws.eventmanager.domain.models.Status;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PermissionGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Models.StatusGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.EnumMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnumMapperTest {
    private final EnumMapper mapper = new EnumMapper();

    //permission tests
    @Test
    void testToModelPermission() {
        PermissionGQL permissionGQL = PermissionGQL.STAFF;
        Permission permission = mapper.toModel(permissionGQL);
        assertNotNull(permission);
        assertEquals(Permission.STAFF, permission);
    }
    @Test
    void testToModelNullPermission() {
        Permission permission = mapper.toModel((PermissionGQL) null);
        assertNull(permission);
    }

    @Test
    void testToModelGQLPermission() {
        Permission permission = Permission.STAFF;
        PermissionGQL permissionGQL = mapper.toModelGQL(permission);
        assertNotNull(permissionGQL);
        assertEquals(PermissionGQL.STAFF, permissionGQL);
    }

    @Test
    void testToModelGQLNullPermission() {
        PermissionGQL permissionGQL = mapper.toModelGQL((Permission) null);
        assertNull(permissionGQL);
    }

    //status tests
    @Test
    void testToModelStatus() {
        StatusGQL statusGQL = StatusGQL.OPEN;
        Status status = mapper.toModel(statusGQL);
        assertNotNull(status);
        assertEquals(Status.OPEN, status);
    }

    @Test
    void testToModelNullStatus() {
        Status status = mapper.toModel((StatusGQL) null);
        assertNull(status);
    }

    @Test
    void testToModelGQLStatus() {
        Status status = Status.OPEN;
        StatusGQL statusGQL = mapper.toModelGQL(status);
        assertNotNull(statusGQL);
        assertEquals(StatusGQL.OPEN, statusGQL);
    }

    @Test
    void testToModelGQLNullStatus() {
        StatusGQL statusGQL = mapper.toModelGQL((Status) null);
        assertNull(statusGQL);
    }
}
