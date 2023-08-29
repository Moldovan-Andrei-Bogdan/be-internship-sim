package com.mecorp.controller;

import com.mecorp.enums.Fields;
import com.mecorp.facade.ProductFacade;
import com.mecorp.facade.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @GetMapping("")
    public List<ProductDto> findAll(@RequestParam(name = "fields") Fields productFields, @RequestParam(name = "include-categories") boolean includeCategories) {
        return this.productFacade.findAll(productFields, includeCategories);
    }
}
