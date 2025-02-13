package com.thws.eventmanager.infrastructure.components.persistence.adapter;

import com.thws.eventmanager.domain.port.out.GenericPersistenceOutport;
import com.thws.eventmanager.infrastructure.components.persistence.PersistenceManager;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import org.slf4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericPersistenceAdapter<T, ID> implements GenericPersistenceOutport<T, ID>, AutoCloseable {
    private final PersistenceManager persistenceManager;
    public final EntityManager entityManager;
    private final Class<T> entityClass;
    private static final Logger logger = LoggerFactory.getLogger(GenericPersistenceAdapter.class);

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
    public List<T> searchByCriteria(List<String> fieldNames, List<Object> values) {
        logger.info("Executing searchByCriteria...");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        EntityType<T> entityType = entityManager.getMetamodel().entity(entityClass); // Get Metamodel for entity T

        List<Predicate> predicates = new ArrayList<>();
        for (int i = 0; i < fieldNames.size(); i++) {
            String fieldName = fieldNames.get(i);
            Object value = values.get(i);

            logger.info("Checking field: {} with value: {}", fieldName, value);

            /*

                GraphQL being a dummy.
                This is required to handle related entity fields.
                Don't touch this.

             */

            if (fieldName.contains("_")) {
                String[] parts = fieldName.split("_", 2);
                if (parts.length == 2) {
                    String relatedEntityNameLower = parts[0].toLowerCase(); // Lowercase for case-insensitive comparison
                    String relatedField = parts[1];
                    String actualRelatedEntityFieldName = null;

                    for (Attribute<? super T, ?> attribute : entityType.getAttributes()) {
                        if (attribute.getName().toLowerCase().equals(relatedEntityNameLower)) {
                            actualRelatedEntityFieldName = attribute.getName();
                            break;
                        }
                    }

                    if (actualRelatedEntityFieldName != null) {
                        try {
                            predicates.add(cb.equal(root.get(actualRelatedEntityFieldName).get(relatedField), value));
                            logger.info("Detected and handled related entity field (case-insensitive): {} using actual field name: {}", fieldName, actualRelatedEntityFieldName);
                        } catch (IllegalArgumentException e) {
                            logger.warn("Related entity field structure detected but invalid field: {}, falling back to direct field.", fieldName);
                            predicates.add(cb.equal(root.get(fieldName), value)); // Fallback to direct field if related field is invalid
                        }
                    } else {
                        logger.warn("Related entity name not found (case-insensitive): {}, falling back to direct field: {}", parts[0], fieldName);
                        predicates.add(cb.equal(root.get(fieldName), value)); // Fallback if related entity name not found
                    }
                } else {
                    logger.info("Treating as direct field due to split issue: {}", fieldName);
                    predicates.add(cb.equal(root.get(fieldName), value));
                }
            } else {
                logger.info("Treating as direct field: {}", fieldName);
                predicates.add(cb.equal(root.get(fieldName), value));
            }
        }

        cq.where(predicates.toArray(new Predicate[0]));

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
