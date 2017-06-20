package ru.kolaer.permit.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
    public Page<ShortPermitEntity> findShortAll(Integer number, Integer pageSize, Integer sort, boolean findRemoved) {
        return this.findShortAll(number, pageSize, sort, findRemoved, null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ShortPermitEntity> findShortAll(Integer number, Integer pageSize, Integer sort, boolean findRemoved, String search) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<ShortPermitEntity> fromCount = countQuery.from(ShortPermitEntity.class);
        CriteriaQuery<Long> selectCount = countQuery.select(criteriaBuilder.count(fromCount.get("id")));

        Predicate removed = null;

        if(!findRemoved) {
            removed = criteriaBuilder.equal(fromCount.get("removed"), false);
        }

        Predicate searchOr = null;

        if(StringUtils.hasText(search)) {
            searchOr = criteriaBuilder.or(criteriaBuilder.like(fromCount.get("serialNumber"), "%" + search + "%"),
                    criteriaBuilder.like(fromCount.get("name"), "%" + search + "%"));
        }

        if(removed != null && searchOr != null) {
            selectCount = selectCount.where(criteriaBuilder.and(removed, searchOr));
        } else if(removed != null) {
            selectCount = selectCount.where(removed);
        } else if(searchOr != null) {
            selectCount = selectCount.where(searchOr);
        }

        final Long count = currentSession
                .createQuery(selectCount)
                .getSingleResult();

        final CriteriaQuery<ShortPermitEntity> selectQuery = criteriaBuilder.createQuery(ShortPermitEntity.class);
        final Root<ShortPermitEntity> fromSelect = selectQuery.from(ShortPermitEntity.class);
        CriteriaQuery<ShortPermitEntity> select = selectQuery.select(fromSelect);

        if(!findRemoved) {
            removed = criteriaBuilder.equal(fromCount.get("removed"), false);
        }

        if(StringUtils.hasText(search)) {
            searchOr = criteriaBuilder.or(criteriaBuilder.like(fromCount.get("serialNumber"), "%" + search + "%"),
                    criteriaBuilder.like(fromCount.get("name"), "%" + search + "%"));
        }

        if(removed != null && searchOr != null) {
            select = select.where(criteriaBuilder.and(removed, searchOr));
        } else if(removed != null) {
            select = select.where(removed);
        } else if(searchOr != null) {
            select = select.where(searchOr);
        }

        switch (sort) {
            case 2: select = select.orderBy(criteriaBuilder.desc(fromSelect.get("extendedPermit"))); break;
            case 1: select = select.orderBy(criteriaBuilder.desc(fromSelect.get("startWork"))); break;
            case 0:
            default: select = select.orderBy(criteriaBuilder.desc(fromSelect.get("dateWritePermit")));
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
    public WorkPermitEntity findWorkById(Long id) {
        return this.sessionFactory.getCurrentSession().get(WorkPermitEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public EventPermitEntity findEventById(Long id) {
        return this.sessionFactory.getCurrentSession().get(EventPermitEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public PeoplePermitEntity findPeopleById(Long id) {
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
    public void deleteExecutor(Long id, Long executor) {
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
                .createQuery("SELECT id FROM PermitEntity WHERE serialNumber = :serialNumber")
                .setParameter("serialNumber", serialNumber)
                .uniqueResultOptional()
                .isPresent();
    }

    @Override
    @Transactional
    public boolean setStatus(Long id, String status) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("UPDATE PermitEntity p SET p.status = :status WHERE p.id = :id")
                .setParameter("id", id)
                .setParameter("status", status)
                .executeUpdate() > 0;
    }

    @Override
    @Transactional
    public boolean setStatus(List<Long> ids, String status) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("UPDATE PermitEntity p SET p.status = :status WHERE p.id IN(:id)")
                .setParameterList("id", ids)
                .setParameter("status", status)
                .executeUpdate() > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public String findSerialNumberById(Long id) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT p.serialNumber FROM PermitEntity p WHERE p.id = :id", String.class)
                .setParameter("id", id)
                .uniqueResultOptional()
                .orElse("");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findAllByStatusAndOverdue(String status) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT p.id FROM PermitEntity p WHERE p.status = :status AND p.extendedPermit <= CURRENT_TIMESTAMP()", Long.class)
                .setParameter("status", status)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findAllByStatusAndStartWork(String status) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT p.id FROM PermitEntity p WHERE p.status = :status AND p.startWork <= CURRENT_TIMESTAMP()", Long.class)
                .setParameter("status", status)
                .list();
    }

    @Override
    @Transactional
    public boolean setCompletePermit(Long id, boolean b) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("UPDATE PermitEntity p SET p.complete = :complete WHERE p.id = :id")
                .setParameter("id", id)
                .setParameter("complete", b)
                .executeUpdate() > 0;
    }
}
