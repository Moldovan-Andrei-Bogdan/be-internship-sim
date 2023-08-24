package com.mecorp.facade;

import com.mecorp.facade.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryFacade {
    List<CategoryDto> findAll();

    Optional<CategoryDto> save(CategoryDto categoryDto);

    Optional<CategoryDto> findById(Long id);

    boolean deleteById(Long id);
}
