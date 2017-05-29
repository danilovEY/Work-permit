package ru.kolaer.permit.service;

import ru.kolaer.permit.entity.EmployeeEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface EmployeePageService extends BasePageService<EmployeeEntity> {

    EmployeeEntity getByPersonnelNumber(Integer personnelNumber);
    EmployeeEntity getByUsername(String username);

    Integer getIdByPersonnelNumber(Integer personnelNumber);


}
