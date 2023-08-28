package com.mecorp.facade.impl;

import com.mecorp.facade.ProductFacade;
import com.mecorp.facade.converter.Converter;
import com.mecorp.facade.dto.ProductDto;
import com.mecorp.model.Category;
import com.mecorp.model.Product;
import com.mecorp.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

public class DefaultProductFacade implements ProductFacade {
    private final ProductService productService;
    @Qualifier("basicProductConverter")
    Converter<Product, ProductDto> basicProductConverter;
    @Qualifier("basicProductReverseConverter")
    Converter<ProductDto, Product> basicProductReverseConverter;
    @Qualifier("fullProductReverseConverter")
    Converter<ProductDto, Product> fullProductReverseConverter;
    @Qualifier("fullProductWithCategoriesConverter")
    Converter<Product, ProductDto> fullProductWithCategoriesConverter;
    @Qualifier("fullProductWithCategoriesReverseConverter")
    Converter<ProductDto, Product> fullProductWithCategoriesReverseConverter;

    public DefaultProductFacade(ProductService productService) {
        this.productService = productService;
    }
    @Override
    public List<ProductDto> findAll() {
        return null;
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDto> save(ProductDto entity) {
        Optional<Product> productOptional;

        if (entity.getCategoriesIds() == null || entity.getCategoriesIds().isEmpty()) {
            productOptional = this.productService.save(this.fullProductReverseConverter.convert(entity));
        } else {
            productOptional = this.productService.save(this.fullProductWithCategoriesReverseConverter.convert(entity));
        }

        return productOptional.map(this.basicProductConverter::convert);
    }

    @Override
    public boolean delete(ProductDto entity) {
        return false;
    }

    @Override
    public Optional<ProductDto> update(ProductDto entity) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    public Converter<Product, ProductDto> getBasicProductConverter() {
        return basicProductConverter;
    }

    public void setBasicProductConverter(Converter<Product, ProductDto> basicProductConverter) {
        this.basicProductConverter = basicProductConverter;
    }

    public Converter<ProductDto, Product> getBasicProductReverseConverter() {
        return basicProductReverseConverter;
    }

    public void setBasicProductReverseConverter(Converter<ProductDto, Product> basicProductReverseConverter) {
        this.basicProductReverseConverter = basicProductReverseConverter;
    }

    public Converter<ProductDto, Product> getFullProductReverseConverter() {
        return fullProductReverseConverter;
    }

    public void setFullProductReverseConverter(Converter<ProductDto, Product> fullProductReverseConverter) {
        this.fullProductReverseConverter = fullProductReverseConverter;
    }

    public Converter<Product, ProductDto> getFullProductWithCategoriesConverter() {
        return fullProductWithCategoriesConverter;
    }

    public void setFullProductWithCategoriesConverter(Converter<Product, ProductDto> fullProductWithCategoriesConverter) {
        this.fullProductWithCategoriesConverter = fullProductWithCategoriesConverter;
    }

    public Converter<ProductDto, Product> getFullProductWithCategoriesReverseConverter() {
        return fullProductWithCategoriesReverseConverter;
    }

    public void setFullProductWithCategoriesReverseConverter(Converter<ProductDto, Product> fullProductWithCategoriesReverseConverter) {
        this.fullProductWithCategoriesReverseConverter = fullProductWithCategoriesReverseConverter;
    }
}
