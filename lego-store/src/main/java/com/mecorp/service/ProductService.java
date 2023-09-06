package com.mecorp.service;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id) throws NotFoundException;

    Product save(Product entity) throws GeneralException;

    boolean delete(Product entity);

    Product update(Product entity) throws GeneralException;

    boolean deleteById(Long id) throws NotFoundException;
}
