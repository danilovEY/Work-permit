package ru.kolaer.permit.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Danilov on 24.05.2017.
 */
@Repository
public class PermitPageDaoImpl extends BasePageDaoAbstract<PermitEntity> implements PermitPageDao {

    public PermitPageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<PermitEntity> getEntityClass() {
        return PermitEntity.class;
    }

    @Override
    public PermitEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_PERMIT;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ShortPermitEntity> findShortAll(Integer number, Integer pageSize, Integer sort, boolean findRemoved) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<ShortPermitEntity> fromCount = countQuery.from(ShortPermitEntity.class);
        CriteriaQuery<Long> selectCount = countQuery.select(criteriaBuilder.count(fromCount.get("id")));

        if(!findRemoved)
            selectCount = selectCount.where(criteriaBuilder.equal(fromCount.get("removed"), false));

        final Long count = currentSession
                .createQuery(selectCount)
                .getSingleResult();

        final CriteriaQuery<ShortPermitEntity> selectQuery = criteriaBuilder.createQuery(ShortPermitEntity.class);
        final Root<ShortPermitEntity> fromSelect = selectQuery.from(ShortPermitEntity.class);
        CriteriaQuery<ShortPermitEntity> select = selectQuery.select(fromSelect);

        if(!findRemoved)
            select = select.where(criteriaBuilder.equal(fromSelect.get("removed"), false));

        switch (sort) {
            case 1: select = select.orderBy(criteriaBuilder.desc(fromSelect.get("startWork"))); break;
            case 0:
            default: select = select.orderBy(criteriaBuilder.desc(fromSelect.get("extendedPermit")));
        }

        TypedQuery<ShortPermitEntity> typedQuery = currentSession
                .createQuery(select);

        if(number > 0) {
            typedQuery = typedQuery
                    .setFirstResult((number - 1) * pageSize)
                    .setMaxResults(pageSize);
        }

        final List<ShortPermitEntity> resultList = typedQuery.getResultList();

        return new Page<>(number,
                (count / pageSize) + 1,
                pageSize,
                Optional.ofNullable(resultList).orElse(Collections.emptyList()));
    }

    @Override
    @Transactional(readOnly = true)
    public WorkPermitEntity findWorkById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(WorkPermitEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public EventPermitEntity findEventById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(EventPermitEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public PeoplePermitEntity findPeopleById(Integer id) {
        return this.sessionFactory.getCurrentSession().get(PeoplePermitEntity.class, id);
    }

    @Override
    @Transactional
    public WorkPermitEntity update(WorkPermitEntity workPermitEntity) {
        this.sessionFactory.getCurrentSession().update(workPermitEntity);
        return workPermitEntity;
    }

    @Override
    @Transactional
    public PeoplePermitEntity update(PeoplePermitEntity peoplePermitEntity) {
        this.sessionFactory.getCurrentSession().update(peoplePermitEntity);
        return peoplePermitEntity;
    }

    @Override
    @Transactional
    public EventPermitEntity update(EventPermitEntity eventPermitEntity) {
        this.sessionFactory.getCurrentSession().update(eventPermitEntity);
        return eventPermitEntity;
    }

    @Override
    @Transactional
    public void deleteExecutor(Integer id, Integer executor) {
        this.sessionFactory.getCurrentSession()
                .createNativeQuery("DELETE FROM executors_permit WHERE permit_id = :permit AND executor_id = :executor")
        .setParameter("permit", id)
        .setParameter("executor", executor)
        .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existSerialNumber(String serialNumber) {
        return this.sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT id FROM permit WHERE serialNumber = :serialNumber")
                .setParameter("serialNumber", serialNumber)
                .uniqueResultOptional()
                .isPresent();
    }

    @Override
    @Transactional
    public boolean setStatus(Integer id, String status) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("UPDATE PermitEntity p SET p.status = :status WHERE p.id = :id")
                .setParameter("id", id)
                .setParameter("status", status)
                .executeUpdate() > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public String findSerialNumberById(Integer id) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT p.serialNumber FROM PermitEntity p WHERE p.id = :id", String.class)
                .setParameter("id", id)
                .uniqueResultOptional()
                .orElse("");
    }
}
