package com.mecorp.facade.populator.impl;

import com.mecorp.facade.converter.Converter;
import com.mecorp.facade.dto.CategoryDto;
import com.mecorp.facade.dto.ProductDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Category;
import com.mecorp.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class CategoriesProductPopulator implements Populator<Product, ProductDto> {
    @Qualifier("basicCategoryConverter")
    private Converter<Category, CategoryDto> basicCategoryConverter;

    @Override
    public void populate(Product product, ProductDto productDto) {
        List<Category> categories = product.getCategories();
        productDto.setCategories(basicCategoryConverter.convertAll(categories));
    }

    public Converter<Category, CategoryDto> getBasicCategoryConverter() {
        return basicCategoryConverter;
    }

    public void setBasicCategoryConverter(Converter<Category, CategoryDto> basicCategoryConverter) {
        this.basicCategoryConverter = basicCategoryConverter;
    }
}
