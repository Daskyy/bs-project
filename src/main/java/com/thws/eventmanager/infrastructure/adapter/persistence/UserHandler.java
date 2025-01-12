package com.thws.eventmanager.infrastructure.adapter.persistence;

import com.thws.eventmanager.application.port.out.UserRepository;
import com.thws.eventmanager.application.database.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserHandler implements UserRepository {
    private static final String PERSISTENCE_UNIT_NAME = "eventmanager";
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public UserHandler() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void saveUser(UserEntity user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return entityManager.createQuery("FROM UserEntity", UserEntity.class).getResultList();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
