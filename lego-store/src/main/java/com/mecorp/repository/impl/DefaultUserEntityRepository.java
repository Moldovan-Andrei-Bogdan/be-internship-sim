package com.mecorp.repository.impl;

import com.mecorp.model.UserEntity;
import com.mecorp.repository.UserEntityRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class DefaultUserEntityRepository extends DefaultAbstractRepository<UserEntity, Long> implements UserEntityRepository {
    public DefaultUserEntityRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public UserEntity findByEmail(String email) {
        String hqlString = "SELECT user FROM UserEntity user WHERE user.email = :email";
        @SuppressWarnings("unchecked")
        final Query<UserEntity> query = this.getSession().createQuery(hqlString);
        query.setParameter("email", email);

        return query.getSingleResult();
    }
}
