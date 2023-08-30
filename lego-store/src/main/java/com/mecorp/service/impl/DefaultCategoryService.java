package com.mecorp.service.impl;

import com.mecorp.model.Category;
import com.mecorp.model.Product;
import com.mecorp.repository.CategoryRepository;
import com.mecorp.service.CategoryService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);

        if (categoryOptional.isEmpty()) return false;

        Category category = categoryOptional.get();

        for (Product product : category.getProducts()) {
            category.removeProduct(product);
        }

        category.setProducts(null);

        this.categoryRepository.delete(category);

        return true;
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
