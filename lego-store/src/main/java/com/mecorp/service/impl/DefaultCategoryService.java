package com.mecorp.service.impl;

import com.mecorp.model.Category;
import com.mecorp.repository.CategoryRepository;
import com.mecorp.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> update(Category category) {
        return this.categoryRepository.update(category);
    }

    @Override
    public List<Category> getCategoriesById(List<Long> categoryIds) {
        return this.categoryRepository.getCategoriesById(categoryIds);
    }
}
