package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.NotificationPageDao;
import ru.kolaer.permit.entity.NotificationEntity;

import java.util.List;

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
    public Class<NotificationEntity> getEntityClass() {
        return NotificationEntity.class;
    }

    @Override
    public NotificationEntity getEmptyEntity() {
        return null;
    }
}
