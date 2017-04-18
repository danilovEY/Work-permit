package ru.kolaer.permit.dao;

import lombok.NonNull;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface AccountPageDao extends BasePageDao<AccountEntity> {
    EmployeeEntity findEmployeeByIdAccount(@NonNull Integer id);
    List<RoleEntity> findRoleByIdAccount(@NonNull Integer id);

    @Override
    default AccountEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_ACCOUNT;
    }
}
