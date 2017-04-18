package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.entity.EmployeeEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
@Repository
public class EmployeePageDaoImpl extends BasePageDaoAbstract<EmployeeEntity> implements EmployeePageDao {

    public EmployeePageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<EmployeeEntity> getEntityClass() {
        return EmployeeEntity.class;
    }
}
