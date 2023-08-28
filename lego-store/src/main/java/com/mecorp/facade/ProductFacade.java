package com.mecorp.facade;

import com.mecorp.facade.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductFacade {
    List<ProductDto> findAll();

    Optional<ProductDto> findById(Long id);

    Optional<ProductDto> save(ProductDto entity);

    boolean delete(ProductDto entity);

    Optional<ProductDto> update(ProductDto entity);

    boolean deleteById(Long id);
}
