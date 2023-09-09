package com.mecorp.repository.impl;

import com.mecorp.enums.PriceRangeType;
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
    private final String IN_STOCK_FILTER_QUERY = " p.stock > 0 ";
    private final String CATEGORIES_FILTER_QUERY = " c.name in :categoryNames ";
    private final String PRICE_BETWEEN_QUERY = " p.price > :minValue and p.price < :maxValue ";

    DefaultProductRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> findAllInStock(PageRequest pageRequest) {

        boolean specifiedCategories = (pageRequest.getCategoryNames() != null);

        String hqlString = "select distinct p" +
                            ALL_PRODUCTS_QUERY +
                            (specifiedCategories ? " join p.categories c " : "") +
                            " where " +
                            (specifiedCategories ? CATEGORIES_FILTER_QUERY + " and " : "") +
                            IN_STOCK_FILTER_QUERY +
                            " and " +
                            PRICE_BETWEEN_QUERY +
                            " order by " + pageRequest.getSortType().getValue();

        final Query<Product> query = this.getSession().createQuery(hqlString, this.persistentClass);
        query.setParameter("minValue", pageRequest.getMinPrice());
        query.setParameter("maxValue", pageRequest.getMaxPrice());

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
                " and " +
                PRICE_BETWEEN_QUERY;

        final Query<Product> query = this.getSession().createQuery(hqlString, Product.class);
        query.setParameter("minValue", pageRequest.getMinPrice());
        query.setParameter("maxValue", pageRequest.getMaxPrice());

        if (specifiedCategories) {
            query.setParameter("categoryNames", pageRequest.getCategoryNames());
        }

        return query.getResultList().size();
    }

    @Override
    public Set<String> getCategoriesByPriceRange(Double minValue, Double maxValue) {
        String hqlString = "select distinct c.name" +
                            ALL_PRODUCTS_QUERY +
                            " join p.categories c " +
                            " where " +
                            PRICE_BETWEEN_QUERY;

        final Query<String> query = this.getSession().createQuery(hqlString, String.class);
        query.setParameter("minValue", minValue);
        query.setParameter("maxValue", maxValue);
        List<String> result = query.getResultList();

        return new HashSet<>(result);
    }

    @Override
    public boolean isPriceRangeAvailable(Set<String> categories, PriceRangeType priceRangeType) {
        boolean specifiedCategories = (categories != null);

        String hqlString = "select count(*)" +
                ALL_PRODUCTS_QUERY +
                (specifiedCategories ? " join p.categories c " : "") +
                " where " +
                (specifiedCategories ? CATEGORIES_FILTER_QUERY + " and " : "") +
                IN_STOCK_FILTER_QUERY +
                " and " +
                PRICE_BETWEEN_QUERY;

        final Query<Long> query = this.getSession().createQuery(hqlString, Long.class);
        query.setParameter("minValue", priceRangeType.minValue);
        query.setParameter("maxValue", priceRangeType.maxValue);

        if (specifiedCategories) {
            query.setParameter("categoryNames", categories);
        }

        return query.uniqueResult() > 0;
    }
}
