package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    @Transactional(readOnly = true)
    public EmployeeEntity findByPersonnelNumber(Integer personnelNumber) {
        return (EmployeeEntity) this.sessionFactory.getCurrentSession()
                .createQuery("FROM EmployeeEntity WHERE personnelNumber = :personnelNumber")
                .setParameter("personnelNumber", personnelNumber)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer findIdByPersonnelNumber(Integer personnelNumber) {
        return (Integer) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT id FROM EmployeeEntity WHERE personnelNumber = :personnelNumber")
                .setParameter("personnelNumber", personnelNumber)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeEntity findByUsername(String username) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM EmployeeEntity WHERE username = :username", EmployeeEntity.class)
                .setParameter("username", username)
                .uniqueResult();
    }
}
