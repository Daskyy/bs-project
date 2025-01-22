package com.thws.eventmanager.infrastructure.components.persistence.entities;

import com.thws.eventmanager.domain.models.Permission;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity implements PersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "permission", nullable = false)
    private Permission permission;

    public UserEntity() {
    }

    public UserEntity(String name, String email, String password, Permission permission) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.permission = permission;
    }

    public String getEmail() {
            return email;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
