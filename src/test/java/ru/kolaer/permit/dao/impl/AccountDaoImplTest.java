package ru.kolaer.permit.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.dao.AccountDao;
import ru.kolaer.permit.dao.BaseTestDao;
import ru.kolaer.permit.entity.AccountEntity;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by danilovey on 17.04.2017.
 */
@DatabaseSetup("/test-config/db/accounts.xml")
public class AccountDaoImplTest extends BaseTestDao {

    @Autowired
    private AccountDao accountDao;

    @Test
    @ExpectedDatabase
    public void persistAccount() {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPassword("password1");
        accountEntity.setUsername("user1");

        final AccountEntity persist = accountDao.persist(accountEntity);

        assertNotNull(persist.getId());
    }

    @Test
    public void getAllAccount() {
        final List<AccountEntity> all = this.accountDao.findAll();
        System.out.println(all.size());
        assertTrue(all.size() > 0);
    }
}