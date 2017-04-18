package ru.kolaer.permit.dao.impl;

import lombok.NonNull;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        final EmployeeEntity employeeEntity = (EmployeeEntity) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT emp FROM AccountEntity a JOIN a.employee as emp WHERE a.id = :id")
                .setParameter("id", id)
                .uniqueResult();

        return Optional
                .ofNullable(employeeEntity)
                .orElse(this.getEmptyEntity().getEmployee());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleEntity> findRoleByIdAccount(@NonNull Integer id) {
        final List<RoleEntity> roles = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT roles FROM AccountEntity a JOIN a.roles as emp WHERE a.id = :id", RoleEntity.class)
                .setParameter("id", id)
                .list();

        return Optional
                .ofNullable(roles)
                .orElse(Collections.emptyList());
    }
}
