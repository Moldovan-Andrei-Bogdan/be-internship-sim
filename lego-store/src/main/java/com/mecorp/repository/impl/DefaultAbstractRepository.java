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
    public Optional<ENTITY> findById(PK id) {
        return Optional.ofNullable(this.getSession().find(this.persistentClass, id));
    }

    @Override
    public Optional<ENTITY> save(ENTITY entity) {
        this.getSession().persist(entity);
        return Optional.of(entity);
    }

    @Override
    public boolean delete(ENTITY entity) {
        this.getSession().remove(entity);

        return true;
    }

    @Override
    public Optional<ENTITY> update(ENTITY entity) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(PK id) {
        final Optional<ENTITY> entityOptional = this.findById(id);

        return entityOptional.filter(this::delete).isPresent();

    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
