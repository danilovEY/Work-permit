package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.DepartmentPageDao;
import ru.kolaer.permit.entity.DepartmentEntity;

/**
 * Created by danilovey on 18.04.2017.
 */
@Repository
public class DepartmentPageDaoImpl extends BasePageDaoAbstract<DepartmentEntity> implements DepartmentPageDao {


    public DepartmentPageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<DepartmentEntity> getEntityClass() {
        return DepartmentEntity.class;
    }
}
