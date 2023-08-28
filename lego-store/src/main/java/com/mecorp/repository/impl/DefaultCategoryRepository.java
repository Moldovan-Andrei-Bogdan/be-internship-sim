package com.mecorp.repository.impl;

import com.mecorp.model.Category;
import com.mecorp.repository.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DefaultCategoryRepository extends DefaultAbstractRepository<Category, Long> implements CategoryRepository {
    DefaultCategoryRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Category> getCategoriesById(List<Long> categoryIds) {
        @SuppressWarnings("unchecked")
        final Query<Category> query = this.getSession().createQuery("SELECT category FROM Category category where category.id in (:categoryIds)");
        Session session = this.getSession();
        query.setParameter("categoryIds", categoryIds);
        List<Category> rst = query.getResultList();
        return query.getResultList();
    }
}
