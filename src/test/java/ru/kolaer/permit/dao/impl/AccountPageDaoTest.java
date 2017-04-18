package ru.kolaer.permit.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.dao.BaseTestDao;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.entity.EmployeeEntity;

import static org.junit.Assert.*;

/**
 * Created by danilovey on 18.04.2017.
 */
@DatabaseSetup("/test-config/db/accounts.xml")
public class AccountPageDaoTest extends BaseTestDao {

    @Autowired
    private AccountPageDao accountPageDao;

    @Test
    public void findFirstAccountWithEmployee(){
        final AccountEntity account = accountPageDao.findById(1);
        assertNotNull(account);

        final EmployeeEntity employeeEntity = this.accountPageDao.findEmployeeByIdAccount(account.getId());
        assertNotNull(employeeEntity);
        System.out.println(employeeEntity.getBirthday());
    }

}