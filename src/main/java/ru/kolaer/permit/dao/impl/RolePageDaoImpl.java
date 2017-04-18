package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.RolePageDao;
import ru.kolaer.permit.entity.RoleEntity;

/**
 * Created by danilovey on 18.04.2017.
 */
@Repository
public class RolePageDaoImpl extends BasePageDaoAbstract<RoleEntity> implements RolePageDao {

    public RolePageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<RoleEntity> getEntityClass() {
        return RoleEntity.class;
    }
}
