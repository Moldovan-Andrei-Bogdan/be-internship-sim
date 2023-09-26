package com.mecorp.repository;

import com.mecorp.model.UserEntity;

public interface UserEntityRepository extends AbstractRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
