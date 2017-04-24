package ru.kolaer.permit.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.AccountDtoDao;
import ru.kolaer.permit.dto.AccountDto;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

/**
 * Created by danilovey on 24.04.2017.
 */
@Repository
@RequiredArgsConstructor
public class AccountDtoDaoImpl implements AccountDtoDao {
    private final SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public AccountDto findAccountByIdEmployee(@NonNull Integer idEmployee) {
        final Object[] values = (Object[]) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT id, username, password FROM EmployeeEntity WHERE id = :id")
                .setParameter("id", idEmployee)
                .uniqueResult();

        final AccountDto account = new AccountDto();
        account.setEmployeeId(Integer.valueOf(values[0].toString()));
        account.setUsername(values[1].toString());
        account.setPassword(values[2].toString());

        final List<RoleEntity> roles = this.sessionFactory.getCurrentSession()
                .createQuery("FROM RoleEntity WHERE employeeId = :employeeId")
                .setParameter("employeeId", account.getEmployeeId())
                .list();

        account.setRoles(roles);

        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findAccountByUsername(@NonNull String username) {
        final Object[] values = (Object[]) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT id, username, password FROM EmployeeEntity WHERE username = :username")
                .setParameter("username", username)
                .uniqueResult();

        final AccountDto account = new AccountDto();
        account.setEmployeeId(Integer.valueOf(values[0].toString()));
        account.setUsername(values[1].toString());
        account.setPassword(values[2].toString());

        final List<RoleEntity> roles = this.sessionFactory.getCurrentSession()
                .createQuery("FROM RoleEntity WHERE employeeId = :employeeId")
                .setParameter("employeeId", account.getEmployeeId())
                .list();

        account.setRoles(roles);

        return account;
    }
}
