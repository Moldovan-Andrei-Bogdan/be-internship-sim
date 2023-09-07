package com.mecorp.facade.impl;

import com.mecorp.enums.Fields;
import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.ProductFacade;
import com.mecorp.facade.converter.Converter;
import com.mecorp.facade.dto.PageRequest;
import com.mecorp.facade.dto.PageResponse;
import com.mecorp.facade.dto.ProductDto;
import com.mecorp.model.Category;
import com.mecorp.model.Product;
import com.mecorp.service.CategoryService;
import com.mecorp.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Convert;
import javax.transaction.Transactional;
import java.util.List;

public class DefaultProductFacade implements ProductFacade {
    private final ProductService productService;
    private final CategoryService categoryService;

    /// BASIC FIELDS
    @Qualifier("basicProductConverter")
    Converter<Product, ProductDto> basicProductConverter;
    @Qualifier("basicProductReverseConverter")
    Converter<ProductDto, Product> basicProductReverseConverter;

    /// SIMPLE FIELDS
    @Qualifier("simpleProductConverter")
    Converter<Product, ProductDto> simpleProductConverter;
    @Qualifier("simpleProductReverseConverter")
    Converter<ProductDto, Product> simpleProductReverseConverter;

    /// FULL FIELDS
    @Qualifier("fullProductConverter")
    Converter<Product, ProductDto> fullProductConverter;
    @Qualifier("fullProductReverseConverter")
    Converter<ProductDto, Product> fullProductReverseConverter;

    /// PAGE REQUEST
    @Qualifier("productsPageResponseConverter")
    Converter<PageResponse<Product>, PageResponse<ProductDto>> productsPageResponseConverter;

    public DefaultProductFacade(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @Override
    @Transactional
    public List<ProductDto> findAll(Fields productFields) {
        List<Product> products = this.productService.findAll();

        if (productFields == Fields.BASIC) {
            return this.basicProductConverter.convertAll(products);
        }

        if (productFields == Fields.SIMPLE) {
            return this.simpleProductConverter.convertAll(products);
        }

        if (productFields == Fields.FULL) {
            return this.fullProductConverter.convertAll(products);
        }

        return null;
    }

    @Override
    public ProductDto findById(Long id, Fields productFields) throws NotFoundException {
        Product product = this.productService.findById(id);

        if (productFields == Fields.BASIC) {
            return this.basicProductConverter.convert(product);
        }

        if (productFields == Fields.SIMPLE) {
            return this.simpleProductConverter.convert(product);
        }

        if (productFields == Fields.FULL) {
            return this.fullProductConverter.convert(product);
        }

        return null;
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto entity) throws GeneralException {
        Product product = this.fullProductReverseConverter.convert(entity);

        if (entity.getCategoriesIds() != null && !entity.getCategoriesIds().isEmpty()) {
            product.setCategories(this.categoryService.getCategoriesById(entity.getCategoriesIds()));
        }

        Product savedProduct = this.productService.save(product);

        return this.basicProductConverter.convert(savedProduct);
    }

    @Override
    public boolean delete(ProductDto entity) {
        return false;
    }

    @Override
    @Transactional
    public ProductDto update(Long id, ProductDto entity) throws NotFoundException, GeneralException {
        Product product = this.productService.findById(id);

        Product convertedProduct = this.fullProductReverseConverter.convert(entity);

        if (entity.getCategoriesIds() != null && !entity.getCategoriesIds().isEmpty()) {
            List<Category> categories = this.categoryService.getCategoriesById(entity.getCategoriesIds());
            convertedProduct.setCategories(categories);
        }
        convertedProduct.setId(product.getId());

        Product updatedProduct = this.productService.update(convertedProduct);

        return this.basicProductConverter.convert(updatedProduct);
    }

    @Override
    public boolean deleteById(Long id) throws NotFoundException {
        return this.productService.deleteById(id);
    }

    @Override
    public PageResponse<ProductDto> findAllInStock(PageRequest pageRequest) {
        PageResponse<Product> pageResponse = this.productService.findAllInStock(pageRequest);

        return this.productsPageResponseConverter.convert(pageResponse);
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

    public Converter<Product, ProductDto> getSimpleProductConverter() {
        return simpleProductConverter;
    }

    public void setSimpleProductConverter(Converter<Product, ProductDto> simpleProductConverter) {
        this.simpleProductConverter = simpleProductConverter;
    }

    public Converter<ProductDto, Product> getSimpleProductReverseConverter() {
        return simpleProductReverseConverter;
    }

    public void setSimpleProductReverseConverter(Converter<ProductDto, Product> simpleProductReverseConverter) {
        this.simpleProductReverseConverter = simpleProductReverseConverter;
    }

    public Converter<Product, ProductDto> getFullProductConverter() {
        return fullProductConverter;
    }

    public void setFullProductConverter(Converter<Product, ProductDto> fullProductConverter) {
        this.fullProductConverter = fullProductConverter;
    }

    public Converter<ProductDto, Product> getFullProductReverseConverter() {
        return fullProductReverseConverter;
    }

    public void setFullProductReverseConverter(Converter<ProductDto, Product> fullProductReverseConverter) {
        this.fullProductReverseConverter = fullProductReverseConverter;
    }

    public Converter<PageResponse<Product>, PageResponse<ProductDto>> getProductsPageResponseConverter() {
        return productsPageResponseConverter;
    }

    public void setProductsPageResponseConverter(Converter<PageResponse<Product>, PageResponse<ProductDto>> productsPageResponseConverter) {
        this.productsPageResponseConverter = productsPageResponseConverter;
    }
}
