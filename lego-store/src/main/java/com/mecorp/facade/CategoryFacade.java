package com.mecorp.facade;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryFacade {
    List<CategoryDto> findAll();

    CategoryDto save(CategoryDto categoryDto) throws GeneralException;

    CategoryDto findById(Long id) throws NotFoundException;

    boolean deleteById(Long id) throws NotFoundException, GeneralException;

    CategoryDto update(Long id, CategoryDto categoryDto) throws NotFoundException, GeneralException;
}
