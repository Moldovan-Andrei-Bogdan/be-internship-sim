package com.mecorp.repository;

import com.mecorp.facade.dto.PageRequest;
import com.mecorp.model.Product;

import java.util.List;

public interface ProductRepository extends AbstractRepository<Product, Long> {
    List<Product> findAllInStock(PageRequest pageRequest);

    Integer getCountInStock(PageRequest pageRequest);
}
