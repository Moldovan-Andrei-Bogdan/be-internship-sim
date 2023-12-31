package com.mecorp.facade.populator.impl;

import com.mecorp.facade.dto.ProductDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Product;

public class BasicProductReversePopulator implements Populator<ProductDto, Product> {
    @Override
    public void populate(ProductDto productDto, Product product) {
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setEan(productDto.getEan());
        product.setPrice(productDto.getPrice());
    }
}
