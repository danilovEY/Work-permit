package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.FullRolePageDao;
import ru.kolaer.permit.entity.FullRoleEntity;

import java.util.List;

/**
 * Created by danilovey on 18.04.2017.
 */
@Repository
public class FullRolePageDaoImpl extends BasePageDaoAbstract<FullRoleEntity> implements FullRolePageDao {

    public FullRolePageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<FullRoleEntity> getEntityClass() {
        return FullRoleEntity.class;
    }

    @Override
    public List<FullRoleEntity> findRolesByIdEmployee(Integer idEmp) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM FullRoleEntity r WHERE r.employee.id = :id")
                .setParameter("id", idEmp)
                .list();
    }
}
