package com.mecorp.facade.populator.impl;

import com.mecorp.facade.dto.ProductDto;
import com.mecorp.facade.populator.Populator;
import com.mecorp.model.Product;

public class StockProductPopulator implements Populator<Product, ProductDto> {
    @Override
    public void populate(Product product, ProductDto productDto) {
        productDto.setStock(product.getStock());
    }
}
