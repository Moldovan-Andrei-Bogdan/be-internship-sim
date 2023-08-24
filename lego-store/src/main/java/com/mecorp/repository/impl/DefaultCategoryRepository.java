package com.mecorp.repository.impl;

import com.mecorp.model.Category;
import com.mecorp.repository.CategoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

public class DefaultCategoryRepository extends DefaultAbstractRepository<Category, Long> implements CategoryRepository {
    DefaultCategoryRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
