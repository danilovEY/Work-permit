package ru.kolaer.permit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.service.AccountPageService;
import ru.kolaer.permit.service.BasePageServiceAbstract;

/**
 * Created by danilovey on 18.04.2017.
 */
@Service
public class AccountPageServiceImpl extends BasePageServiceAbstract<AccountEntity> implements AccountPageService {
    private final AccountPageDao dao;

    @Autowired
    public AccountPageServiceImpl(AccountPageDao dao) {
        super(dao);
        this.dao = dao;
    }

}
