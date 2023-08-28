package com.mecorp.facade.populator.impl;

import com.mecorp.facade.dto.ProductDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Product;
import com.mecorp.service.CategoryService;
import com.mecorp.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;

public class CategoriesProductReversePopulator implements Populator<ProductDto, Product> {
    @Qualifier("categoryService")
    private CategoryService categoryService;

    @Override
    public void populate(ProductDto productDto, Product product) {
        product.setCategories(categoryService.getCategoriesById(productDto.getCategoriesIds()));
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
