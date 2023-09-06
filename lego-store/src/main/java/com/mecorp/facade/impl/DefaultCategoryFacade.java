package com.mecorp.facade.impl;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.CategoryFacade;
import com.mecorp.facade.converter.Converter;
import com.mecorp.facade.dto.CategoryDto;
import com.mecorp.model.Category;
import com.mecorp.service.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.List;
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
    public CategoryDto save(CategoryDto categoryDto) throws GeneralException {
        Category category = this.basicCategoryReverseConverter.convert(categoryDto);
        Category savedCategory = this.categoryService.save(category);

        return this.basicCategoryConverter.convert(savedCategory);
    }

    @Override
    public CategoryDto findById(Long id) throws NotFoundException {
        Category category = this.categoryService.findById(id);

        return this.basicCategoryConverter.convert(category);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws NotFoundException, GeneralException {
        return this.categoryService.deleteById(id);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) throws NotFoundException, GeneralException {
        Category category = this.categoryService.findById(id);
        categoryDto.setId(category.getId());

        Category convertedCategory = this.basicCategoryReverseConverter.convert(categoryDto);
        Category updatedCategory = this.categoryService.update(convertedCategory);

        return this.basicCategoryConverter.convert(updatedCategory);
    }
}
