package com.mecorp.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractRepository<ENTITY, PK extends Serializable> {
    List<ENTITY> findAll();

    Optional<ENTITY> findById(PK id, boolean allowTransactionActions);

    Optional<ENTITY> save(ENTITY entity, boolean allowTransactionActions);

    void delete(ENTITY entity, boolean allowTransactionActions);

    Optional<ENTITY> update(ENTITY entity, boolean allowTransactionActions);

    Optional<ENTITY> deleteById(PK id, boolean allowTransactionActions);
}
