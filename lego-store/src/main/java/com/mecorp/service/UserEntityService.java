package com.mecorp.service;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.model.Product;
import com.mecorp.model.UserEntity;

import java.util.List;

public interface UserEntityService {
    List<UserEntity> findAll();

    UserEntity findById(Long id) throws NotFoundException;

    UserEntity save(UserEntity entity) throws GeneralException;

    boolean delete(Product entity);

    UserEntity update(UserEntity entity) throws GeneralException;

    boolean deleteById(Long id) throws NotFoundException;

    UserEntity findByEmail(String email) throws NotFoundException;
}
