package com.mecorp.controller;

import com.mecorp.facade.ProductFacade;
import com.mecorp.facade.dto.ProductDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping("")
    public ProductDto save(@Valid @RequestBody ProductDto productDto) {
        Optional<ProductDto> response = this.productFacade.save(productDto);

        if (response.isEmpty()) {
            throw new RuntimeException("Invalid or incomplete data for a product");
        }

        return response.get();
    }
}
