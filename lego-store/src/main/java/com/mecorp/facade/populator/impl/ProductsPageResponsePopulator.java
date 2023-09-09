package com.mecorp.facade.populator.impl;

import com.mecorp.facade.converter.Converter;
import com.mecorp.facade.dto.PageRequest;
import com.mecorp.facade.dto.PageResponse;
import com.mecorp.facade.dto.ProductDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Product;

import java.util.List;

public class ProductsPageResponsePopulator implements Populator<PageResponse<Product>, PageResponse<ProductDto>> {
    private Converter<Product, ProductDto> basicProductConverter;

    @Override
    public void populate(PageResponse<Product> productPageResponse, PageResponse<ProductDto> productDtoPageResponse) {
        List<Product> products = productPageResponse.getResultList();
        List<ProductDto> convertedProducts = this.basicProductConverter.convertAll(products);

        productDtoPageResponse.setResultList(convertedProducts);
        productDtoPageResponse.setNrOfElemsOnPage(productPageResponse.getNrOfElemsOnPage());
        productDtoPageResponse.setNrOfTotalProducts(productPageResponse.getNrOfTotalProducts());
        productDtoPageResponse.setNrOfPages(productPageResponse.getNrOfPages());
        productDtoPageResponse.setFirstElem(productPageResponse.getFirstElem());
    }

    public Converter<Product, ProductDto> getBasicProductConverter() {
        return basicProductConverter;
    }

    public void setBasicProductConverter(Converter<Product, ProductDto> basicProductConverter) {
        this.basicProductConverter = basicProductConverter;
    }
}
