package com.mecorp.service.impl;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.model.Category;
import com.mecorp.model.Product;
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
    public Category save(Category category) throws GeneralException {
        return this.categoryRepository.save(category)
                .orElseThrow(() -> new GeneralException("Could not save category"));
    }

    @Override
    public Category findById(Long id) throws NotFoundException {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find such category"));
    }

    @Override
    public boolean deleteById(Long id) throws NotFoundException, GeneralException{
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);

        if (categoryOptional.isEmpty()) throw new NotFoundException("Could not find such category");

        Category category = categoryOptional.get();

        for (Product product : category.getProducts()) {
            category.removeProduct(product);
        }

        category.setProducts(null);

        boolean isCategoryDeleted = this.categoryRepository.delete(category);

        if (!isCategoryDeleted) throw new GeneralException("Could not delete category");

        return true;
    }

    @Override
    public Category update(Category category) throws GeneralException {
        return this.categoryRepository.update(category)
                .orElseThrow(() -> new GeneralException("Could not update category"));
    }

    @Override
    public List<Category> getCategoriesById(List<Long> categoryIds) {
        return this.categoryRepository.getCategoriesById(categoryIds);
    }
}
