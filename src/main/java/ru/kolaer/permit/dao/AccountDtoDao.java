package ru.kolaer.permit.dao;

import lombok.NonNull;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dto.AccountDto;
import ru.kolaer.permit.entity.EmployeeEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface AccountDtoDao {
    AccountDto findAccountByIdEmployee(@NonNull Integer idEmployee);
    AccountDto findAccountByUsername(@NonNull String username);
}
