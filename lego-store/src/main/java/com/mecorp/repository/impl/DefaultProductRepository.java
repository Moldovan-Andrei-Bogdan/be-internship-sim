package com.mecorp.repository.impl;

import com.mecorp.facade.dto.PageRequest;
import com.mecorp.model.Product;
import com.mecorp.repository.ProductRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DefaultProductRepository extends DefaultAbstractRepository<Product, Long> implements ProductRepository {
    private final String ALL_IN_STOCK_QUERY = "select distinct p from Product p where p.stock > 0";
    private final String ORDER_BY = "order by ";
    DefaultProductRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> findAllInStock(PageRequest pageRequest) {
        String hqlString = ALL_IN_STOCK_QUERY + ORDER_BY + pageRequest.getSortType().getValue();
        final Query<Product> query = this.getSession().createQuery(hqlString, this.persistentClass);

        Integer pageSize = pageRequest.getPageSize();
        Integer currentPage = pageRequest.getPageNumber();
        int firstResult = (currentPage - 1) * pageSize;

        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public Integer getCountInStock(PageRequest pageRequest) {
        final Query<Product> query = this.getSession().createQuery(ALL_IN_STOCK_QUERY, this.persistentClass);

        return query.getResultList().size();
    }
}
