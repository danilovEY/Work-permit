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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public List<T> findAll(boolean findRemoved) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        final CriteriaQuery<T> query = criteriaBuilder
                .createQuery(this.getEntityClass());

        final Root<T> from = query.from(this.getEntityClass());

        CriteriaQuery<T> select = query.select(from);

        if(!findRemoved)
            select = select.where(criteriaBuilder.equal(from.get("removed"), false));

        final List<T> resultList = currentSession
                .createQuery(select)
                .getResultList();

        return Optional.ofNullable(resultList).orElse(Collections.emptyList());
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(@NonNull Integer id, boolean findRemoved) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        final CriteriaQuery<T> query = criteriaBuilder
                .createQuery(this.getEntityClass());

        final Root<T> from = query.from(this.getEntityClass());

        final CriteriaQuery<T> select = query.select(from);
        Predicate byId = criteriaBuilder.equal(from.get("id"), id);

        if(!findRemoved)
            byId = criteriaBuilder.and(byId, criteriaBuilder.equal(from.get("removed"), false));

        final T result = currentSession.createQuery(select.where(byId)).uniqueResult();

        return Optional.ofNullable(result).orElse(this.getEmptyEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(@NonNull Integer number, @NonNull Integer pageSize, boolean findRemoved) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<T> fromCount = countQuery.from(this.getEntityClass());
        CriteriaQuery<Long> selectCount = countQuery.select(criteriaBuilder.count(fromCount.get("id")));

        if(!findRemoved)
            selectCount = selectCount.where(criteriaBuilder.equal(fromCount.get("removed"), false));

        final Long count = currentSession
                .createQuery(selectCount)
                .getSingleResult();

        final CriteriaQuery<T> selectQuery = criteriaBuilder.createQuery(this.getEntityClass());
        final Root<T> fromSelect = selectQuery.from(this.getEntityClass());
        CriteriaQuery<T> select = selectQuery.select(fromSelect);

        if(!findRemoved)
            select = select.where(criteriaBuilder.equal(fromSelect.get("removed"), true));

        TypedQuery<T> typedQuery = currentSession
                .createQuery(selectQuery);

        if(number > 0) {
            typedQuery = typedQuery
                    .setFirstResult((number - 1) * pageSize)
                    .setMaxResults(pageSize);
        }

        final List<T> resultList = typedQuery.getResultList();

        return new Page<>(number,
                (count / pageSize) + 1,
                pageSize,
                Optional.ofNullable(resultList).orElse(Collections.emptyList()));
    }

    @Override
    public T delete(T entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
        entity.setId(null);
        return entity;
    }

    @Override
    public List<T> deleteAll(List<T> entities) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final List<T> results = this.batchForeach(entities, this.batchSize, currentSession, currentSession::delete);

        results.forEach(e -> e.setId(null));
        return results;
    }
}
