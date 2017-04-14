package ru.kolaer.permit.dao.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.AccountDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.AccountEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Value("${hibernate.batch.size}")
    private Integer batchSize;

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public AccountDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @Transactional
    public AccountEntity persist(@NonNull AccountEntity entity) {
        this.entityManagerFactory.createEntityManager().persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public List<AccountEntity> persistAll(@NonNull List<AccountEntity> entity) {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        return this.batchForeach(entity, this.batchSize, entityManager, entityManager::persist);
    }

    @Override
    @Transactional
    public AccountEntity update(@NonNull AccountEntity entity) {
        return this.entityManagerFactory.createEntityManager().merge(entity);
    }

    @Override
    @Transactional
    public List<AccountEntity> updateAll(@NonNull List<AccountEntity> entity) {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        return this.batchForeach(entity, this.batchSize, entityManager, entityManager::merge);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountEntity> findAll() {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        final CriteriaQuery<AccountEntity> query = entityManager.getCriteriaBuilder()
                .createQuery(AccountEntity.class);

        return entityManager.createQuery(query.select(query.from(AccountEntity.class))).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountEntity findById(@NonNull Integer id) {
        return this.entityManagerFactory.createEntityManager().find(AccountEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountEntity> findAll(@NonNull Integer number, @NonNull Integer pageSize) {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

        final Long count = entityManager
                .createQuery(countQuery.select(criteriaBuilder.count(countQuery.from(AccountEntity.class).get("id"))))
                .getSingleResult();

        final CriteriaQuery<AccountEntity> selectQuery = criteriaBuilder.createQuery(AccountEntity.class);

        TypedQuery<AccountEntity> accountEntityTypedQuery = entityManager
                .createQuery(selectQuery.select(selectQuery.from(AccountEntity.class)));

        if(number > 0) {
            accountEntityTypedQuery = accountEntityTypedQuery
                    .setFirstResult((number - 1) * pageSize)
                    .setMaxResults(pageSize);
        }

        final List<AccountEntity> resultList = accountEntityTypedQuery.getResultList();

        return new Page<>(number, count, pageSize, resultList);
    }
}
