package com.mecorp.facade.populator.impl;

import com.mecorp.facade.dto.ProductDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Product;

public class DescriptionProductReversePopulator implements Populator<ProductDto, Product> {
    @Override
    public void populate(ProductDto productDto, Product product) {
        product.setDescription(productDto.getDescription());
    }
}
