package com.mecorp.service;

import com.mecorp.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> save(Product entity);

    boolean delete(Product entity);

    Optional<Product> update(Product entity);

    boolean deleteById(Long id);
}
