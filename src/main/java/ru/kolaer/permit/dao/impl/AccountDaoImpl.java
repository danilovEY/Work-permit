package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.dao.AccountDao;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.entity.AccountEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public class AccountDaoImpl extends BasePageDaoAbstract<AccountEntity> implements AccountDao {

    public AccountDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<AccountEntity> getEntityClass() {
        return AccountEntity.class;
    }
}
