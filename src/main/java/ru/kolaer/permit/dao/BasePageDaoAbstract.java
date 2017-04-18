package ru.kolaer.permit.dao;

import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.BaseEntity;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by danilovey on 18.04.2017.
 */
@Repository
public abstract class BasePageDaoAbstract<T extends BaseEntity> implements BasePageDao<T> {
    @Value("${hibernate.batch.size}")
    private Integer batchSize;

    protected final SessionFactory sessionFactory;

    @Autowired
    public BasePageDaoAbstract(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public T persist(@NonNull T entity) {
        this.sessionFactory.getCurrentSession().persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public List<T> persistAll(@NonNull List<T> entity) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        return this.batchForeach(entity, this.batchSize, currentSession, currentSession::persist);
    }

    @Override
    @Transactional
    public T update(@NonNull T entity) {
        this.sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    @Override
    @Transactional
    public List<T> updateAll(@NonNull List<T> entity) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        return this.batchForeach(entity, this.batchSize, currentSession, currentSession::update);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaQuery<T> query = currentSession.getCriteriaBuilder()
                .createQuery(this.getEntityClass());

        final List<T> resultList = currentSession
                .createQuery(query.select(query.from(this.getEntityClass())))
                .getResultList();

        return Optional.ofNullable(resultList).orElse(Collections.emptyList());
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(@NonNull Integer id) {
        return Optional
                .ofNullable(this.sessionFactory.getCurrentSession().find(this.getEntityClass(), id))
                .orElse(this.getEmptyEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(@NonNull Integer number, @NonNull Integer pageSize) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

        final Long count = currentSession
                .createQuery(countQuery.select(criteriaBuilder.count(countQuery.from(this.getEntityClass()).get("id"))))
                .getSingleResult();

        final CriteriaQuery<T> selectQuery = criteriaBuilder.createQuery(this.getEntityClass());

        TypedQuery<T> typedQuery = currentSession
                .createQuery(selectQuery.select(selectQuery.from(this.getEntityClass())));

        if(number > 0) {
            typedQuery = typedQuery
                    .setFirstResult((number - 1) * pageSize)
                    .setMaxResults(pageSize);
        }

        final List<T> resultList = typedQuery.getResultList();

        return new Page<>(number,
                count,
                pageSize,
                Optional.ofNullable(resultList).orElse(Collections.emptyList()));
    }
}
