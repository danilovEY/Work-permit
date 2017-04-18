package ru.kolaer.permit.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.dao.AccountDao;
import ru.kolaer.permit.dao.BaseTestDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.AccountEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by danilovey on 17.04.2017.
 */
@DatabaseSetup("/test-config/db/accounts.xml")
public class UniformDaoTestByAccount extends BaseTestDao {
    private static final int DEFAULT_ENTITY_IN_BASE = 1;

    @Autowired
    private AccountDao accountDao;

    @Test
    public void persist() {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPassword("password1");
        accountEntity.setUsername("user1");

        final AccountEntity persist = this.accountDao.persist(accountEntity);
        assertNotNull(persist);
        assertNotNull(persist.getId());
    }

    @Test
    public void findAll() {
        final List<AccountEntity> all = this.accountDao.findAll();
        assertNotNull(all);
        assertTrue(all.size() > 0);
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
                this.accountDao.persistAll(Arrays.asList(accountEntity, accountEntity2));

        assertNotNull(persistList);
        assertTrue(persistList.size() > DEFAULT_ENTITY_IN_BASE);
        persistList.stream()
                .map(AccountEntity::getId)
                .forEach(Assert::assertNotNull);
    }

    @Test
    public void findById() throws Exception {
        final AccountEntity entity = this.accountDao.findById(DEFAULT_ENTITY_IN_BASE);
        assertNotNull(entity);
    }

    @Test
    public void update() throws Exception {
        final AccountEntity entity = this.accountDao.findById(DEFAULT_ENTITY_IN_BASE);

        assertNotNull(entity);

        entity.setPassword("123");
        entity.setUsername("321");

        assertNotNull(this.accountDao.update(entity));
    }

    @Test
    public void updateAll() throws Exception {
        final Page<AccountEntity> entityPage = this.accountDao.findAll(1, 3);

        assertNotNull(entityPage);
        assertNotNull(entityPage.getData());
        assertTrue(entityPage.getNumber().equals(1)
                && entityPage.getPageSize().equals(3));


        final List<AccountEntity> entitiesToUpdate = entityPage.getData().stream().map(acc -> {
            acc.setPassword(UUID.randomUUID().toString());
            return acc;
        }).collect(Collectors.toList());

        assertEquals(this.accountDao.updateAll(entitiesToUpdate).size(), entitiesToUpdate.size());
    }

}