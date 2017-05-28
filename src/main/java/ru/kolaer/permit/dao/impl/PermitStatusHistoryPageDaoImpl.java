package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.PermitStatusHistoryPageDao;
import ru.kolaer.permit.entity.PermitStatusHistoryEntity;

import java.util.List;

/**
 * Created by Danilov on 27.05.2017.
 */
@Repository
public class PermitStatusHistoryPageDaoImpl extends BasePageDaoAbstract<PermitStatusHistoryEntity>
        implements PermitStatusHistoryPageDao {

    public PermitStatusHistoryPageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<PermitStatusHistoryEntity> getEntityClass() {
        return PermitStatusHistoryEntity.class;
    }

    @Override
    public PermitStatusHistoryEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_STATUS_HISTORY;
    }

    @Override
    @Transactional(readOnly = true)
    public PermitStatusHistoryEntity findLastStatusByIdPermit(Integer id) {
        return (PermitStatusHistoryEntity) this.sessionFactory.getCurrentSession()
                .createQuery("FROM PermitStatusHistoryEntity p WHERE p.permit.id = :id ORDER BY p.statusDate DESC")
                .setParameter("id", id)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermitStatusHistoryEntity> findLastStatusByIdPermitRange(List<Integer> id) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM PermitStatusHistoryEntity p WHERE p.permit.id IN(:id) ORDER BY p.statusDate DESC")
                .setParameterList("id", id)
                .list();
    }
}
