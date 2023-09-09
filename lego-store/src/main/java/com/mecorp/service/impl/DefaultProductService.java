package com.mecorp.service.impl;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.dto.PageRequest;
import com.mecorp.facade.dto.PageResponse;
import com.mecorp.model.Product;
import com.mecorp.repository.ProductRepository;
import com.mecorp.service.ProductService;

import java.util.List;
import java.util.Set;

public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) throws NotFoundException {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Product save(Product entity) throws GeneralException {
        return this.productRepository.save(entity)
                .orElseThrow(() -> new GeneralException("Could not save product"));
    }

    @Override
    public boolean delete(Product entity) {
        return false;
    }

    @Override
    public Product update(Product entity) throws GeneralException {
        return this.productRepository.update(entity)
                .orElseThrow(() -> new GeneralException("Could not update category"));
    }

    @Override
    public boolean deleteById(Long id) throws NotFoundException {
        boolean isEntityDeleted = this.productRepository.deleteById(id);

        if (!isEntityDeleted) throw new NotFoundException("Could not find such product");

        return true;
    }

    @Override
    public PageResponse<Product> findAllInStock(PageRequest pageRequest) {
        PageResponse<Product> pageResponse = new PageResponse<>();
        Integer pageSize = pageRequest.getPageSize();
        Integer currentPage = pageRequest.getPageNumber();
        Integer firstElement = (currentPage - 1) * pageSize + 1;

        List<Product> products = this.productRepository.findAllInStock(pageRequest);
        Integer countInStock = this.productRepository.getCountInStock(pageRequest);
        Integer numberOfPages = (countInStock + pageSize - 1) / pageSize;
        Set<String> availableCategories = this.productRepository.getAvailableCategories();

        pageResponse.setResultList(products);
        pageResponse.setNrOfTotalProducts(countInStock);
        pageResponse.setNrOfElemsOnPage(pageSize);
        pageResponse.setNrOfPages(numberOfPages);
        pageResponse.setFirstElem(firstElement);
        pageResponse.setCategoryNames(availableCategories);

        return pageResponse;
    }
}
