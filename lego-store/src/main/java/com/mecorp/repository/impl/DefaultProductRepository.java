package com.mecorp.repository.impl;

import com.mecorp.facade.dto.PageRequest;
import com.mecorp.model.Product;
import com.mecorp.repository.ProductRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DefaultProductRepository extends DefaultAbstractRepository<Product, Long> implements ProductRepository {
    DefaultProductRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> findAllInStock(PageRequest pageRequest) {
        String hqlString = "from Product p order by " + pageRequest.getSortType().getValue();
        final Query<Product> query = this.getSession().createQuery(hqlString, this.persistentClass);

        return query.getResultList();
    }
}
