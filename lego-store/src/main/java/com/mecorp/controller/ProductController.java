package com.mecorp.controller;

import com.mecorp.enums.Fields;
import com.mecorp.enums.SortType;
import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.ProductFacade;
import com.mecorp.facade.dto.PageRequest;
import com.mecorp.facade.dto.PageResponse;
import com.mecorp.facade.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {
    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping("/backoffice/products")
    public ProductDto save(@Valid @RequestBody ProductDto productDto) throws GeneralException {
        return this.productFacade.save(productDto);
    }

    @GetMapping("/backoffice/products")
    public List<ProductDto> findAll(@RequestParam(name = "fields", defaultValue = "BASIC") Fields productFields
    ) {
        return this.productFacade.findAll(productFields);
    }

    @DeleteMapping("/backoffice/products/{id}")
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        this.productFacade.deleteById(id);
    }

    @PutMapping("/backoffice/products/{id}")
    public ProductDto update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) throws NotFoundException, GeneralException {
        return this.productFacade.update(id, productDto);
    }

    @GetMapping("/products")
    public PageResponse<ProductDto> findAllInStock(
            @RequestParam(name = "sort", defaultValue = "PRICE_ASC") String sortType,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "page-size", defaultValue = "1") Integer pageSize,
            @RequestParam(name = "min-price", defaultValue = "0") Double minPrice,
            @RequestParam(name = "max-price", defaultValue = "9999999") Double maxPrice,
            @RequestParam(name = "categories", required = false) Set<String> categories
    ) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setSortType(SortType.getOrDefault(sortType, SortType.PRICE_ASC));
        pageRequest.setPageNumber(page);
        pageRequest.setPageSize(pageSize);
        pageRequest.setCategoryNames(categories);
        pageRequest.setMinPrice(minPrice);
        pageRequest.setMaxPrice(maxPrice);

        return this.productFacade.findAllInStock(pageRequest);
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(
            @PathVariable Long id,
            @RequestParam(name = "fields", defaultValue = "BASIC") Fields productFields
    ) throws NotFoundException {
        return this.productFacade.findById(id, productFields);
    }
}
