package com.thws.eventmanager.infrastructure.components.persistence.handler;

import com.thws.eventmanager.domain.port.out.AddressOutPort;
import com.thws.eventmanager.domain.models.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AddressHandler implements AddressOutPort {
    private static final String PERSISTENCE_UNIT_NAME = "eventmanager";
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public AddressHandler() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void saveAddress(Address address) {
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Address> getAllAddresses() {
        return entityManager.createQuery("FROM AddressEntity", Address.class).getResultList();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
