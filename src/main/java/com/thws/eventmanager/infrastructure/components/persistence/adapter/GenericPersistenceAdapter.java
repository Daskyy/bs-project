package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

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
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entity = entityManager.merge(entity); // 🔹 Merge ensures new and existing entities are handled properly
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save entity: " + e.getMessage(), e);
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
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
    public List<T> searchByCriteria(String fieldName, Object value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        Predicate predicate = cb.equal(root.get(fieldName), value);
        cq.where(predicate);

        TypedQuery<T> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void close() {
        if (persistenceManager != null) {
            persistenceManager.close();
        }
    }



}
