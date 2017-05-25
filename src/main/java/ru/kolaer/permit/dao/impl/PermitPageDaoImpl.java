package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.entity.PermitEntity;

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
}
