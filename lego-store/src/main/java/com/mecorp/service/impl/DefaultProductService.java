package com.mecorp.service.impl;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.model.Product;
import com.mecorp.repository.ProductRepository;
import com.mecorp.service.ProductService;

import java.util.List;

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
    public Product findById(Long id) throws NotFoundException {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Product save(Product entity) throws GeneralException {
        return this.productRepository.save(entity)
                .orElseThrow(() -> new GeneralException("Could not save product"));
    }

    @Override
    public boolean delete(Product entity) {
        return false;
    }

    @Override
    public Product update(Product entity) throws GeneralException {
        return this.productRepository.update(entity)
                .orElseThrow(() -> new GeneralException("Could not update category"));
    }

    @Override
    public boolean deleteById(Long id) throws NotFoundException {
        boolean isEntityDeleted = this.productRepository.deleteById(id);

        if (!isEntityDeleted) throw new NotFoundException("Could not find such product");

        return true;
    }
}
