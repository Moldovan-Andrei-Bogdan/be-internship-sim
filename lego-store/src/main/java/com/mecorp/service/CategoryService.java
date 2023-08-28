package com.mecorp.service;

import com.mecorp.model.Category;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> save(Category category);

    Optional<Category> findById(Long id);

    boolean deleteById(Long id);

    Optional<Category> update(Category category);

    List<Category> getCategoriesById(List<Long> categoryIds);
}
