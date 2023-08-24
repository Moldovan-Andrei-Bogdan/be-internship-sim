package com.mecorp.facade.populator.impl;

import com.mecorp.facade.dto.CategoryDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Category;

public class BasicCategoryPopulator implements Populator<Category, CategoryDto> {
    @Override
    public void populate(Category category, CategoryDto categoryDto) {
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
    }
}
