package com.mecorp.repository.impl;

import com.mecorp.facade.dto.PageRequest;
import com.mecorp.model.Product;
import com.mecorp.repository.ProductRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultProductRepository extends DefaultAbstractRepository<Product, Long> implements ProductRepository {
    private final String ALL_PRODUCTS_QUERY = " from Product p ";
    private final String AVAILABLE_CATEGORIES_QUERY = "select distinct c.name from Product p join p.categories c";
    private final String IN_STOCK_FILTER_QUERY = " p.stock > 0 ";
    private final String CATEGORIES_FILTER_QUERY = " c.name in :categoryNames ";

    DefaultProductRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> findAllInStock(PageRequest pageRequest) {

        boolean specifiedCategories = pageRequest.getCategoryNames() != null;

        String hqlString = "select distinct p" +
                            ALL_PRODUCTS_QUERY +
                            (specifiedCategories ? " join p.categories c " : "") +
                            " where " +
                            (specifiedCategories ? CATEGORIES_FILTER_QUERY + " and " : "") +
                            IN_STOCK_FILTER_QUERY +
                            " order by " + pageRequest.getSortType().getValue();

        final Query<Product> query = this.getSession().createQuery(hqlString, this.persistentClass);

        if (specifiedCategories) {
            query.setParameter("categoryNames", pageRequest.getCategoryNames());
        }

        Integer pageSize = pageRequest.getPageSize();
        Integer currentPage = pageRequest.getPageNumber();
        int firstResult = (currentPage - 1) * pageSize;

        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public Integer getCountInStock(PageRequest pageRequest) {
        boolean specifiedCategories = pageRequest.getCategoryNames() != null;

        String hqlString = "select distinct p" +
                ALL_PRODUCTS_QUERY +
                (specifiedCategories ? " join p.categories c " : "") +
                " where " +
                (specifiedCategories ? CATEGORIES_FILTER_QUERY + " and " : "") +
                IN_STOCK_FILTER_QUERY +
                " order by " + pageRequest.getSortType().getValue();

        final Query<Product> query = this.getSession().createQuery(hqlString, this.persistentClass);

        if (specifiedCategories) {
            query.setParameter("categoryNames", pageRequest.getCategoryNames());
        }

        return query.getResultList().size();
    }

    @Override
    public Set<String> getAvailableCategories() {
        final Query<String> query = this.getSession().createQuery(AVAILABLE_CATEGORIES_QUERY, String.class);

        return new HashSet<>(query.getResultList());
    }
}
