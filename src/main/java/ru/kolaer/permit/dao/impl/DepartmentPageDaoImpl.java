package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.DepartmentPageDao;
import ru.kolaer.permit.entity.DepartmentEntity;

import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public boolean existDepartment(DepartmentEntity department) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT id FROM DepartmentEntity WHERE name = :depName AND id <> :id")
                .setParameter("id", Optional.ofNullable(department.getId()).orElse(-1))
                .setParameter("depName", department.getName())
                .uniqueResultOptional()
                .isPresent();
    }
}
