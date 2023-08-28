package com.mecorp.repository;

import com.mecorp.model.Category;

import java.util.List;

public interface CategoryRepository extends AbstractRepository<Category, Long> {
    List<Category> getCategoriesById(List<Long> categoryIds);
}
