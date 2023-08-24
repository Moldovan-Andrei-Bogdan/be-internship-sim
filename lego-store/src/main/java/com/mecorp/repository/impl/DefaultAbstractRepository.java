package com.mecorp.repository.impl;

import com.mecorp.repository.AbstractRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Transactional
public class DefaultAbstractRepository<ENTITY, PK extends Serializable> implements AbstractRepository<ENTITY, PK> {

    private final Class<ENTITY> persistentClass;

    private final SessionFactory sessionFactory;

    public DefaultAbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.persistentClass = (Class<ENTITY>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<ENTITY> findAll() {
        String queryString = "from " + this.persistentClass.getSimpleName();
        final Query<ENTITY> query = this.getSession().createQuery(queryString, persistentClass);

        return query.getResultList();
    }

    @Override
    public Optional<ENTITY> findById(PK id, boolean allowCommit) {
        return Optional.ofNullable(this.getSession().find(this.persistentClass, id));
    }

    @Override
    public Optional<ENTITY> save(ENTITY entity, boolean allowCommit) {
        this.getSession().persist(entity);
        return Optional.of(entity);
    }

    @Override
    public void delete(ENTITY entity, boolean allowTransactionActions) {
    }

    @Override
    public Optional<ENTITY> update(ENTITY entity, boolean allowTransactionActions) {
        return Optional.empty();
    }

    @Override
    public Optional<ENTITY> deleteById(PK id, boolean allowTransactionActions) {
        return Optional.empty();
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
