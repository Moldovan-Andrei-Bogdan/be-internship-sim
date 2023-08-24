package com.mecorp.controller;

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
    public CategoryDto findById(@PathVariable Long id) {
        Optional<CategoryDto> categoryDto = this.categoryFacade.findById(id);

        if (categoryDto.isEmpty()) {
            throw new NotFoundException("No category with this id has been found");
        }

        return categoryDto.get();
    }

    @PostMapping("")
    public CategoryDto save(@Valid @RequestBody CategoryDto categoryDto) {
        Optional<CategoryDto> response = this.categoryFacade.save(categoryDto);

        if (response.isEmpty()) {
            throw new RuntimeException("Invalid or incomplete data for a category");
        }

        return response.get();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        boolean isEntityDeleted = this.categoryFacade.deleteById(id);

        if (!isEntityDeleted) {
            throw new NotFoundException("No category with this id has been found");
        }
    }

    @PutMapping("{id}")
    public CategoryDto update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Optional<CategoryDto> response = this.categoryFacade.update(id, categoryDto);

        if (response.isEmpty()) {
            throw new RuntimeException("No category with this id has been found");
        }

        return response.get();
    }
}
