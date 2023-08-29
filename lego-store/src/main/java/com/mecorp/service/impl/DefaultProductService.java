package com.mecorp.service.impl;

import com.mecorp.model.Product;
import com.mecorp.repository.ProductRepository;
import com.mecorp.service.ProductService;

import java.util.List;
import java.util.Optional;

public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> save(Product entity) {
        return this.productRepository.save(entity);
    }

    @Override
    public boolean delete(Product entity) {
        return false;
    }

    @Override
    public Optional<Product> update(Product entity) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
