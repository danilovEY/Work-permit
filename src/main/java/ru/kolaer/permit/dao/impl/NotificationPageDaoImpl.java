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
    public List<NotificationEntity> findNotReadableNotifyToEmployeeId(long employeeId) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM NotificationEntity n WHERE n.to.id = :empId AND n.read = false", NotificationEntity.class)
                .setParameter("empId", employeeId)
                .list();
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
