package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.RolePageDao;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

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

    @Override
    public List<RoleEntity> findRolesByIdEmployee(Integer idEmp) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM RoleEntity WHERE employeeId = :id")
                .setParameter("id", idEmp)
                .list();
    }
}
