package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public abstract class GenericPersistenceAdapter<T, ID> implements GenericPersistenceOutport<T, ID>, AutoCloseable {
    private final PersistenceManager persistenceManager;
    public final EntityManager entityManager;
    private final Class<T> entityClass;

    protected GenericPersistenceAdapter(Class<T> entityClass) {
        this.persistenceManager = PersistenceManager.create();  // Keep it open
        this.entityManager = persistenceManager.getEntityManager();
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
            entityManager.getTransaction().commit();
            return Optional.of(entity);
        }
        entityManager.getTransaction().rollback(); // Rollback if nothing was found
        return Optional.empty();
    }

    @Override
    public void close() {
        if (persistenceManager != null) {
            persistenceManager.close();
        }
    }
}
