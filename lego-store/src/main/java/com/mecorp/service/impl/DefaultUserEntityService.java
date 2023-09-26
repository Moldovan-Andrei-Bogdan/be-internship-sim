package com.mecorp.service.impl;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.model.Product;
import com.mecorp.model.UserEntity;
import com.mecorp.repository.UserEntityRepository;
import com.mecorp.service.UserEntityService;

import java.util.List;

public class DefaultUserEntityService implements UserEntityService {

    private final UserEntityRepository userEntityRepository;

    public DefaultUserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public UserEntity findById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public UserEntity save(UserEntity entity) throws GeneralException {
        return null;
    }

    @Override
    public boolean delete(Product entity) {
        return false;
    }

    @Override
    public UserEntity update(UserEntity entity) throws GeneralException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws NotFoundException {
        return false;
    }

    @Override
    public UserEntity findByEmail(String email) throws NotFoundException {
        return this.userEntityRepository.findByEmail(email);
    }
}
