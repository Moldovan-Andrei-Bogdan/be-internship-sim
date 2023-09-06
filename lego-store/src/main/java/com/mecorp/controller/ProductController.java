package com.mecorp.controller;

import com.mecorp.enums.Fields;
import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.ProductFacade;
import com.mecorp.facade.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping("")
    public ProductDto save(@Valid @RequestBody ProductDto productDto) throws GeneralException {
        return this.productFacade.save(productDto);
    }

    @GetMapping("")
    public List<ProductDto> findAll(@RequestParam(name = "fields", defaultValue = "BASIC") Fields productFields
    ) {
        return this.productFacade.findAll(productFields);
    }

    @GetMapping("{id}")
    public ProductDto findById(
            @PathVariable Long id,
            @RequestParam(name = "fields", defaultValue = "BASIC") Fields productFields
    ) throws NotFoundException {
        return this.productFacade.findById(id, productFields);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        this.productFacade.deleteById(id);
    }

    @PutMapping("{id}")
    public ProductDto update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) throws NotFoundException, GeneralException {
        return this.productFacade.update(id, productDto);
    }
}
