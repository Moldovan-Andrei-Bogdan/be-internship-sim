package com.mecorp.repository;

import com.mecorp.facade.dto.PageRequest;
import com.mecorp.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends AbstractRepository<Product, Long> {
    List<Product> findAllInStock(PageRequest pageRequest);

    Integer getCountInStock(PageRequest pageRequest);

    Set<String> getAvailableCategories();
}
