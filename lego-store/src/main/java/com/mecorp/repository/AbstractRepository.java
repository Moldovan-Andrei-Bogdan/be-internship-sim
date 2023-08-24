package com.mecorp.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractRepository<ENTITY, PK extends Serializable> {
    List<ENTITY> findAll();

    Optional<ENTITY> findById(PK id);

    Optional<ENTITY> save(ENTITY entity);

    boolean delete(ENTITY entity);

    Optional<ENTITY> update(ENTITY entity);

    boolean deleteById(PK id);
}
