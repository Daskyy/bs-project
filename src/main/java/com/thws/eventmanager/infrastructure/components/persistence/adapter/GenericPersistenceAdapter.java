package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public abstract class GenericPersistenceAdapter<T, ID> implements GenericPersistenceOutport<T, ID> {
    public final EntityManager entityManager;
    private final Class<T> entityClass;

    protected GenericPersistenceAdapter(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    @Override
    public Optional<T> deleteById(ID id) {
        entityManager.getTransaction().begin();
        T entity = entityManager.find(entityClass, id);
        if (entity != null) {
            entityManager.remove(entity);
            return Optional.of(entity);
        }
        entityManager.getTransaction().commit();
        return Optional.empty();
    }
}
