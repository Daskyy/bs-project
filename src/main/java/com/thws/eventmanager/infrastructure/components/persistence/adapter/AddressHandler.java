package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;

public class AddressHandler extends GenericPersistenceAdapter<AddressEntity, Long> {
    public AddressHandler() {
        super(AddressEntity.class);
    }
}
