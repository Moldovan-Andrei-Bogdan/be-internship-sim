package com.mecorp.repository.impl;

import com.mecorp.model.Product;
import com.mecorp.repository.ProductRepository;
import org.hibernate.SessionFactory;

public class DefaultProductRepository extends DefaultAbstractRepository<Product, Long> implements ProductRepository {
    DefaultProductRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
