package com.mecorp.facade.populator.impl;

import com.mecorp.facade.dto.ProductDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Product;

public class DescriptionProductPopulator implements Populator<Product, ProductDto> {
    @Override
    public void populate(Product product, ProductDto productDto) {
        productDto.setDescription(product.getDescription());
    }
}
