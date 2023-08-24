package com.mecorp.facade.impl;

import com.mecorp.facade.CategoryFacade;
import com.mecorp.facade.converter.Converter;
import com.mecorp.facade.dto.CategoryDto;
import com.mecorp.model.Category;
import com.mecorp.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultCategoryFacade implements CategoryFacade {
    private final CategoryService categoryService;

    @Qualifier("basicCategoryConverter")
    private final Converter<Category, CategoryDto> basicCategoryConverter;

    @Qualifier("basicCategoryReverseConverter")
    private final Converter<CategoryDto, Category> basicCategoryReverseConverter;

    public DefaultCategoryFacade(
            CategoryService categoryService,
            Converter<Category, CategoryDto> basicCategoryConverter,
            Converter<CategoryDto, Category> basicCategoryReverseConverter
    ) {
        this.categoryService = categoryService;
        this.basicCategoryConverter = basicCategoryConverter;
        this.basicCategoryReverseConverter = basicCategoryReverseConverter;
    }
    @Override
    public List<CategoryDto> findAll() {
        return this.categoryService.findAll()
                .stream()
                .map(this.basicCategoryConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> save(CategoryDto categoryDto) {
        return this.categoryService.save(this.basicCategoryReverseConverter.convert(categoryDto))
                .map(this.basicCategoryConverter::convert);
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return this.categoryService.findById(id).map(this.basicCategoryConverter::convert);
    }
}
