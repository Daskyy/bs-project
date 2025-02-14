package com.thws.eventmanager.infrastructure.GraphQL.InputModels;

import com.thws.eventmanager.infrastructure.GraphQL.Models.PermissionGQL;

import java.util.ArrayList;
import java.util.List;

public class UserCriteriaInput {
    private String name;
    private String email;
    private PermissionGQL permissionGQL;

    public UserCriteriaInput() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPermissionGQL(PermissionGQL permission) {
        this.permissionGQL = permission;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public PermissionGQL getPermissionGQL() {
        return permissionGQL;
    }

    public List<Object> getValues() {
        List<Object> values = new ArrayList<>();
        if (name != null && !name.isBlank()) {
            values.add(name);
        }
        if (email != null && !email.isBlank()) {
            values.add(email);
        }
        if (permissionGQL != null) {
            values.add(permissionGQL);
        }
        return values;
    }

    public List<String> getCriteria() {
        List<String> criteria = new ArrayList<>();
        if (name != null && !name.isBlank()) {
            criteria.add("name");
        }
        if (email != null && !email.isBlank()) {
            criteria.add("email");
        }
        if (permissionGQL != null) {
            criteria.add("permission");
        }
        return criteria;
    }
}
