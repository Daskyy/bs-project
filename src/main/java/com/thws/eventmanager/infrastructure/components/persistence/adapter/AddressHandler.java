package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AddressHandler extends GenericPersistenceAdapter<AddressEntity, Long> {
    public AddressHandler(EntityManager entityManager) {
        super(entityManager, AddressEntity.class);
    }

    public AddressEntity findByStreetCityZip(String street, String city, int zipCode, String country) {
        // JPQL query to find an AddressEntity matching the given criteria
        List<AddressEntity> existingAddresses = entityManager.createQuery(
                        "SELECT a FROM AddressEntity a WHERE a.street = :street AND a.city = :city AND a.zipCode = :zipCode AND a.country = :country",
                        AddressEntity.class)
                .setParameter("street", street)
                .setParameter("city", city)
                .setParameter("zipCode", zipCode)
                .setParameter("country", country)
                .getResultList();

        return existingAddresses.isEmpty() ? null : existingAddresses.get(0);
    }
}
