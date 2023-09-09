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

//    @GetMapping("")
//    public List<ProductDto> findAll(@RequestParam(name = "fields", defaultValue = "BASIC") Fields productFields
//    ) {
//        return this.productFacade.findAll(productFields);
//    }

    @GetMapping("")
    public PageResponse<ProductDto> findAllInStock(
            @RequestParam(name = "sort", defaultValue = "PRICE_ASC") String sortType,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "page-size", defaultValue = "1") Integer pageSize
    ) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setSortType(SortType.getOrDefault(sortType, SortType.PRICE_ASC));
        pageRequest.setPageNumber(page);
        pageRequest.setPageSize(pageSize);

        return this.productFacade.findAllInStock(pageRequest);
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
