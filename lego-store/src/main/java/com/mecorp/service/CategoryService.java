package com.mecorp.service;

import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.model.Category;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Category save(Category category) throws GeneralException;

    Category findById(Long id) throws NotFoundException;

    boolean deleteById(Long id) throws NotFoundException, GeneralException;

    Category update(Category category) throws GeneralException;

    List<Category> getCategoriesById(List<Long> categoryIds);
}
