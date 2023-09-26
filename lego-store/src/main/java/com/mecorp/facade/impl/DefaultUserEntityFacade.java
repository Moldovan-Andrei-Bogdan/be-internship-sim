package com.mecorp.facade.impl;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.UserEntityFacade;
import com.mecorp.facade.dto.ProductDto;
import com.mecorp.model.UserEntity;
import com.mecorp.service.UserEntityService;

import java.util.List;

public class DefaultUserEntityFacade implements UserEntityFacade {
    private final UserEntityService userEntityService;

    public DefaultUserEntityFacade(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
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
    public boolean delete(ProductDto entity) {
        return false;
    }

    @Override
    public UserEntity update(Long id, UserEntity entity) throws NotFoundException, GeneralException {
        return null;
    }

    @Override
    public boolean deleteById(Long id) throws NotFoundException {
        return false;
    }

    @Override
    public UserEntity findByEmail(String email) throws NotFoundException {
        return this.userEntityService.findByEmail(email);
    }
}
