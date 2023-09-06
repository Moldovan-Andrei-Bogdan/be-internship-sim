package com.mecorp.controller;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.CategoryFacade;
import com.mecorp.facade.dto.CategoryDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryFacade categoryFacade;

    public CategoryController(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    @GetMapping("")
    public List<CategoryDto> findAll() {
        return this.categoryFacade.findAll();
    }

    @GetMapping("{id}")
    public CategoryDto findById(@PathVariable Long id) throws NotFoundException {
        return this.categoryFacade.findById(id);
    }

    @PostMapping("")
    public CategoryDto save(@Valid @RequestBody CategoryDto categoryDto) throws GeneralException {
        return this.categoryFacade.save(categoryDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) throws NotFoundException, GeneralException {
        this.categoryFacade.deleteById(id);
    }

    @PutMapping("{id}")
    public CategoryDto update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) throws NotFoundException, GeneralException{
        return this.categoryFacade.update(id, categoryDto);
    }
}
