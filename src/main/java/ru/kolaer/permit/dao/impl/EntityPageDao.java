package ru.kolaer.permit.dao.impl;

import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by danilovey on 14.04.2017.
 */
@Repository
public class EntityPageDao implements EmployeePageDao {

    private final EntityManagerFactory entityManagerFactory;

    @Value("${hibernate.batch.size}")
    private Integer batchSize;

    @Autowired
    public EntityPageDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @Transactional
    public EmployeeEntity persist(@NonNull EmployeeEntity entity) {
        this.entityManagerFactory.createEntityManager().persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public List<EmployeeEntity> persistAll(@NonNull List<EmployeeEntity> entity) {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        return this.batchForeach(entity, batchSize, entityManager, entityManager::persist);
    }

    @Override
    @Transactional
    public EmployeeEntity update(@NonNull EmployeeEntity entity) {
        this.entityManagerFactory.createEntityManager().merge(entity);
        return entity;
    }

    @Override
    @Transactional
    public List<EmployeeEntity> updateAll(@NonNull List<EmployeeEntity> entity) {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        return this.batchForeach(entity, batchSize, entityManager, entityManager::merge);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeEntity> findAll() {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        final CriteriaQuery<EmployeeEntity> query = entityManager.getCriteriaBuilder()
                .createQuery(EmployeeEntity.class);

        return entityManager.createQuery(query.select(query.from(EmployeeEntity.class)))
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeEntity findById(@NonNull Integer id) {
        return this.entityManagerFactory.createEntityManager().find(EmployeeEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeEntity> findAll(@NonNull Integer number, @NonNull Integer pageSize) {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> queryCount = builder.createQuery(Long.class);

        final Long count = entityManager
                .createQuery(queryCount.select(builder.count(queryCount.from(EmployeeEntity.class).get("id"))))
                .getSingleResult();

        final CriteriaQuery<EmployeeEntity> selectEmployeeQuery = builder.createQuery(EmployeeEntity.class);
        final List<EmployeeEntity> resultList = entityManager.createQuery(selectEmployeeQuery
                .select(selectEmployeeQuery.from(EmployeeEntity.class)))
                .setFirstResult((number - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return new Page<>(number, count, pageSize, resultList);
    }
}
