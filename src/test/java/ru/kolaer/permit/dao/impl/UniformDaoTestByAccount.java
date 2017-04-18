package ru.kolaer.permit.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.dao.BaseTestDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.AccountEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by danilovey on 17.04.2017.
 */
@DatabaseSetup("/test-config/db/accounts.xml")
public class UniformDaoTestByAccount extends BaseTestDao {
    private static final int DEFAULT_ENTITY_IN_BASE = 1;

    @Autowired
    private AccountPageDao accountPageDao;

    @Test
    public void persist() {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPassword("password1");
        accountEntity.setUsername("user1");

        final AccountEntity persist = this.accountPageDao.persist(accountEntity);
        assertNotNull(persist.getId());
        assertTrue(persist.getId() > 0);
    }

    @Test
    public void findAll() {
        final List<AccountEntity> all = this.accountPageDao.findAll();
        assertFalse(all.isEmpty());
    }

    @Test
    public void persistAll() throws Exception {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPassword("password1");
        accountEntity.setUsername("user1");

        final AccountEntity accountEntity2 = new AccountEntity();
        accountEntity2.setPassword("password2");
        accountEntity2.setUsername("user2");

        final List<AccountEntity> persistList =
                this.accountPageDao.persistAll(Arrays.asList(accountEntity, accountEntity2));

        assertTrue(persistList.size() > DEFAULT_ENTITY_IN_BASE);
        persistList.stream()
                .map(AccountEntity::getId)
                .forEach(accId -> assertTrue(accId > 0));
    }

    @Test
    public void findById() throws Exception {
        final AccountEntity entity = this.accountPageDao.findById(DEFAULT_ENTITY_IN_BASE);
        assertTrue(entity.getId() > 0);
    }

    @Test
    public void update() throws Exception {
        final AccountEntity entity = this.accountPageDao.findById(DEFAULT_ENTITY_IN_BASE);

        assertTrue(entity.getId() > 0);

        entity.setPassword("123");
        entity.setUsername("321");

        assertEquals(this.accountPageDao.update(entity), entity);
    }

    @Test
    public void updateAll() throws Exception {
        final Page<AccountEntity> entityPage = this.accountPageDao.findAll(1, 3);

        assertFalse(entityPage.getData().isEmpty());

        final List<AccountEntity> entitiesToUpdate = entityPage.getData().stream().map(acc -> {
            acc.setPassword("");
            return acc;
        }).collect(Collectors.toList());

        assertEquals(this.accountPageDao.updateAll(entitiesToUpdate).size(), entitiesToUpdate.size());
    }

    @Test(expected = NullPointerException.class)
    public void throwIfIdNull() {
        final AccountEntity accountEntity = this.accountPageDao.findById(null);
        assertNull(accountEntity);
    }

}