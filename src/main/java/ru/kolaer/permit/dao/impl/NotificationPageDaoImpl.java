package ru.kolaer.permit.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.NotificationPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.NotificationEntity;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by danilovey on 20.06.2017.
 */
@Repository
public class NotificationPageDaoImpl extends BasePageDaoAbstract<NotificationEntity> implements NotificationPageDao {

    public NotificationPageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationEntity> findAllNotReadableNotifyToEmployeeId(long employeeId) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM NotificationEntity n WHERE n.to.id = :empId AND n.read = false ORDER BY n.createDate DESC",
                        NotificationEntity.class)
                .setParameter("empId", employeeId)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationEntity> findLimitNotReadableNotifyToEmployeeId(long employeeId, int limit) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM NotificationEntity n WHERE n.to.id = :empId AND n.read = false ORDER BY n.createDate DESC",
                        NotificationEntity.class)
                .setParameter("empId", employeeId)
                .setMaxResults(limit)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public Long findCountNotReadableNotifyToEmployeeId(long employeeId) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(n.id) FROM NotificationEntity n WHERE n.to.id = :empId AND n.read = false", Long.class)
                .setParameter("empId", employeeId)
                .uniqueResult();
    }

    @Override
    @Transactional
    public Page<NotificationEntity> findAllByEmployeeId(Integer number, Integer pageSize, Long empId) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<NotificationEntity> fromCount = countQuery.from(this.getEntityClass());
        CriteriaQuery<Long> selectCount = countQuery.select(criteriaBuilder.count(fromCount.get("id")));

        selectCount = selectCount.where(criteriaBuilder.equal(countQuery.from(EmployeeEntity.class).get("id"), empId));

        final Long count = currentSession
                .createQuery(selectCount)
                .getSingleResult();

        CriteriaQuery<NotificationEntity> selectQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<NotificationEntity> fromSelect = selectQuery.from(this.getEntityClass());
        CriteriaQuery<NotificationEntity> select = selectQuery.select(fromSelect);

        select = select.where(criteriaBuilder.equal(selectQuery.from(EmployeeEntity.class).get("id"), empId));
        select = select.orderBy(criteriaBuilder.desc(fromSelect.get("createDate")));
        TypedQuery<NotificationEntity> typedQuery = currentSession
                .createQuery(select);

        if(number > 0) {
            typedQuery = typedQuery
                    .setFirstResult((number - 1) * pageSize)
                    .setMaxResults(pageSize);
        }

        final List<NotificationEntity> resultList = typedQuery.getResultList();

        final long totalPage = count == pageSize.longValue() ? 1L : (count / pageSize) + 1;

        return new Page<>(number,
                totalPage,
                pageSize,
                Optional.ofNullable(resultList).orElse(Collections.emptyList()));
    }

    @Override
    public Class<NotificationEntity> getEntityClass() {
        return NotificationEntity.class;
    }

    @Override
    public NotificationEntity getEmptyEntity() {
        return null;
    }
}
