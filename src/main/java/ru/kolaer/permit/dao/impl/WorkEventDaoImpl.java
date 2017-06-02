package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.WorkEventDao;
import ru.kolaer.permit.entity.WorkEvent;

import java.util.List;

/**
 * Created by Danilov on 01.06.2017.
 */
@Repository
public class WorkEventDaoImpl extends BasePageDaoAbstract<WorkEvent> implements WorkEventDao {

    public WorkEventDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkEvent> findByIdPermit(Integer idPermit, boolean findRemoved) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM WorkEvent w WHERE w.permit.id = :id AND w.removed = :remove", WorkEvent.class)
                .setParameter("remove", findRemoved)
                .setParameter("id", idPermit)
                .list();
    }

    @Override
    @Transactional
    public boolean deleteEmployee(Integer idWorkEvent, Integer idEmployee) {
        return this.sessionFactory.getCurrentSession()
                .createNativeQuery("DELETE FROM work_event_employee WHERE event_id = :event AND employee_id = :employee")
                .setParameter("event", idWorkEvent)
                .setParameter("employee", idEmployee)
                .executeUpdate() > 0;
    }

    @Override
    public Class<WorkEvent> getEntityClass() {
        return WorkEvent.class;
    }

    @Override
    public WorkEvent getEmptyEntity() {
        return EmptyObjects.DEFAULT_WORK_EVENT;
    }
}
