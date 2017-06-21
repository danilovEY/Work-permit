package ru.kolaer.permit.dao;

import lombok.NonNull;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dto.AccountDto;
import ru.kolaer.permit.entity.EmployeeEntity;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface AccountDao {
    String ROLE_DB_ADMIN = "ROLE_DB_ADMIN";
    String ROLE_USER = "ROLE_USER";
    String ROLE_APPROVE = "ROLE_APPROVE";
    String ROLE_PERMIT = "ROLE_PERMIT";

    AccountDto findAccountByIdEmployee(@NonNull Integer idEmployee);
    AccountDto findAccountByUsername(@NonNull String username);

    List<Long> findEmployeeIdByRole(String role);
}
