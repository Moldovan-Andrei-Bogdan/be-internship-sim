package com.mecorp.facade;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.dto.ProductDto;
import com.mecorp.model.UserEntity;

import java.util.List;

public interface UserEntityFacade {
    List<UserEntity> findAll();

    UserEntity findById(Long id) throws NotFoundException;

    UserEntity save(UserEntity entity) throws GeneralException;

    boolean delete(ProductDto entity);

    UserEntity update(Long id, UserEntity entity) throws NotFoundException, GeneralException;

    boolean deleteById(Long id) throws NotFoundException;

    UserEntity findByEmail(String email) throws NotFoundException;
}
