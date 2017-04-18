package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.entity.EmployeeEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
@Repository
public class AccountPageDaoImpl extends BasePageDaoAbstract<AccountEntity> implements AccountPageDao {

    public AccountPageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<AccountEntity> getEntityClass() {
        return AccountEntity.class;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeEntity findEmployeeByIdAccount(Integer id) {
        return ((AccountEntity) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT a.emplotee FROM AccountEntity a JOIN FETCH a.employee WHERE a.id = :id")
                .setParameter("id", id)
                .uniqueResult()).getEmployee();
    }


}
