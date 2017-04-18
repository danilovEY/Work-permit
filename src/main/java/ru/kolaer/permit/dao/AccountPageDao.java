package ru.kolaer.permit.dao;

import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.entity.EmployeeEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface AccountPageDao extends BasePageDao<AccountEntity> {
    EmployeeEntity findEmployeeByIdAccount(Integer id);

    @Override
    default AccountEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_ACCOUNT;
    }
}
