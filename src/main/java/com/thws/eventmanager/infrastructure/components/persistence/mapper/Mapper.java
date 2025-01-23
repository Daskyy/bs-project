package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.Model;
import com.thws.eventmanager.infrastructure.components.persistence.entities.PersistenceEntity;

public abstract class Mapper <Mod extends Model, Entity extends PersistenceEntity> {
    public abstract Mod toModel(Entity entity);
    public abstract Entity toEntity(Mod model);
}
