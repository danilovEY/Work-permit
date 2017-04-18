package ru.kolaer.permit.dao;

import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.entity.EmployeeEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface EmployeePageDao extends BasePageDao<EmployeeEntity> {
    @Override
    default EmployeeEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_EMPLOYEE;
    }
}
