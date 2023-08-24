package com.mecorp.service;

import com.mecorp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> save(Category category);

    Optional<Category> findById(Long id);
}
