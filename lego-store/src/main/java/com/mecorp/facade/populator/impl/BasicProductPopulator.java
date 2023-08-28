package com.mecorp.facade.populator.impl;

import com.mecorp.facade.dto.ProductDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Product;

public class BasicProductPopulator implements Populator<Product, ProductDto> {
    @Override
    public void populate(Product product, ProductDto productDto) {
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setEan(product.getEan());
        productDto.setPrice(product.getPrice());
    }
}
