package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.entity.AccountEntity;

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
}
