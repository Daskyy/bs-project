package com.thws.eventmanager.domain.port.out;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public interface GenericPersistenceOutport<T, ID> {
    T save(T entity);
    EntityManager getEntityManager();
    Optional<T> findById(ID id);
    List<T> findAll();
    Optional<T> deleteById(ID id);
    List<T> searchByCriteria(List<String> fieldNames, List<Object> values, int page, int size);
    void close();
    public List<T> searchByCriteria(List<String> fieldNames, List<Object> values);
}
