package com.thws.eventmanager.infrastructure.GraphQL.Models;

import com.thws.eventmanager.domain.models.Permission;

public enum PermissionGQL {
    STAFF, ARTIST, CUSTOMER;

    // Permission -> PermissionGQL
    public static PermissionGQL from(Permission permission) {
        return switch (permission) {
            case STAFF -> STAFF;
            case ARTIST -> ARTIST;
            case CUSTOMER -> CUSTOMER;
        };
    }

    // PermissionGQL -> Permission
    public Permission to() {
        return switch (this) {
            case STAFF -> Permission.STAFF;
            case ARTIST -> Permission.ARTIST;
            case CUSTOMER -> Permission.CUSTOMER;
        };
    }
}
