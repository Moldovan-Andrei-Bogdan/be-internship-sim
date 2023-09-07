package com.mecorp.facade;

import com.mecorp.enums.Fields;
import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.dto.PageRequest;
import com.mecorp.facade.dto.PageResponse;
import com.mecorp.facade.dto.ProductDto;

import java.util.List;

public interface ProductFacade {
    List<ProductDto> findAll(Fields productFields);

    ProductDto findById(Long id, Fields productFields) throws NotFoundException;

    ProductDto save(ProductDto entity) throws GeneralException;

    boolean delete(ProductDto entity);

    ProductDto update(Long id, ProductDto entity) throws NotFoundException, GeneralException;

    boolean deleteById(Long id) throws NotFoundException;

    PageResponse<ProductDto> findAllInStock(PageRequest pageRequest);
}
