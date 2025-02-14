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
            entity = entityManager.merge(entity);
            entityManager.flush();
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

//    @Override
//    public List<T> searchByCriteria(String fieldName, Object value) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(entityClass);
//        Root<T> root = cq.from(entityClass);
//
//        Predicate predicate = cb.equal(root.get(fieldName), value);
//        cq.where(predicate);
//
//        TypedQuery<T> query = entityManager.createQuery(cq);
//        return query.getResultList();
//    }

//    @Override
//    public List<T> searchByCriteria(String fieldName, Object value, int page, int size) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> cq = cb.createQuery(entityClass);
//        Root<T> root = cq.from(entityClass);
//
//        Predicate predicate = cb.equal(root.get(fieldName), value);
//        cq.where(predicate);
//
//        TypedQuery<T> query = entityManager.createQuery(cq);
//
//        // Apply Pagination
//        query.setFirstResult(page * size);
//        query.setMaxResults(size);
//
//        return query.getResultList();
//    }



    @Override
    public List<T> searchByCriteria(List<String> fieldNames, List<Object> values) {
        logger.info("Executing searchByCriteria...");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        EntityType<T> entityType = entityManager.getMetamodel().entity(entityClass);

        List<Predicate> predicates = new ArrayList<>();
        for (int i = 0; i < fieldNames.size(); i++) {
            String fieldName = fieldNames.get(i);
            Object value = values.get(i);

            logger.info("Checking field: {} with value: {}", fieldName, value);

            if (fieldName.contains("_")) {
                String[] parts = fieldName.split("_", 2);
                if (parts.length == 2) {
                    String relatedEntityNameLower = parts[0].toLowerCase();
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
                            Attribute<? super T, ?> attribute = entityType.getAttribute(actualRelatedEntityFieldName);
                            if (attribute.isCollection()) {
                                Join<T, ?> join = root.join(actualRelatedEntityFieldName);
                                predicates.add(cb.equal(join.get(relatedField), value));
                                logger.info("Detected and handled ManyToMany relation: {}.{}", actualRelatedEntityFieldName, relatedField);
                            } else {
                                predicates.add(cb.equal(root.get(actualRelatedEntityFieldName).get(relatedField), value));
                                logger.info("Detected and handled related entity field: {} using actual field name: {}", fieldName, actualRelatedEntityFieldName);
                            }
                        } catch (IllegalArgumentException e) {
                            logger.warn("Related entity field detected but invalid field: {}, falling back to direct field.", fieldName);
                            predicates.add(cb.equal(root.get(fieldName), value));
                        }
                    } else {
                        logger.warn("Related entity name not found: {}, falling back to direct field: {}", parts[0], fieldName);
                        predicates.add(cb.equal(root.get(fieldName), value));
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
    public List<T> searchByCriteria(List<String> fieldNames, List<Object> values, int page, int size) {
        logger.info("Executing searchByCriteria... Page: {}, Size: {}", page, size);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        EntityType<T> entityType = entityManager.getMetamodel().entity(entityClass);

        List<Predicate> predicates = new ArrayList<>();
        for (int i = 0; i < fieldNames.size(); i++) {
            String fieldName = fieldNames.get(i);
            Object value = values.get(i);

            logger.info("Checking field: {} with value: {}", fieldName, value);

            if (fieldName.contains("_")) {
                String[] parts = fieldName.split("_", 2);
                if (parts.length == 2) {
                    String relatedEntityNameLower = parts[0].toLowerCase();
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
                            Attribute<? super T, ?> attribute = entityType.getAttribute(actualRelatedEntityFieldName);
                            if (attribute.isCollection()) {
                                Join<T, ?> join = root.join(actualRelatedEntityFieldName);
                                predicates.add(cb.equal(join.get(relatedField), value));
                                logger.info("Detected and handled ManyToMany relation: {}.{}", actualRelatedEntityFieldName, relatedField);
                            } else {
                                predicates.add(cb.equal(root.get(actualRelatedEntityFieldName).get(relatedField), value));
                                logger.info("Detected and handled related entity field: {} using actual field name: {}", fieldName, actualRelatedEntityFieldName);
                            }
                        } catch (IllegalArgumentException e) {
                            logger.warn("Related entity field detected but invalid field: {}, falling back to direct field.", fieldName);
                            predicates.add(cb.equal(root.get(fieldName), value));
                        }
                    } else {
                        logger.warn("Related entity name not found: {}, falling back to direct field: {}", parts[0], fieldName);
                        predicates.add(cb.equal(root.get(fieldName), value));
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

        // Apply Pagination
        query.setFirstResult((page-1) * size);
        query.setMaxResults(size);

        return query.getResultList();
    }



    @Override
    public void close() {
        if (persistenceManager != null) {
            persistenceManager.close();
        }
    }



}
