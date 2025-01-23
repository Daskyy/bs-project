package com.thws.eventmanager.domain.port.out;

import java.util.List;
import java.util.Optional;

public interface GenericPersistenceOutport<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    Optional<T> deleteById(ID id);
}
