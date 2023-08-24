package com.mecorp.facade.populator.impl;

import com.mecorp.facade.dto.CategoryDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Category;

public class BasicCategoryReversePopulator implements Populator<CategoryDto, Category> {
    @Override
    public void populate(CategoryDto categoryDto, Category category) {
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
    }
}
