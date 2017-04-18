package ru.kolaer.permit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.EmployeePageService;

/**
 * Created by danilovey on 14.04.2017.
 */
//@Service
public class EmployeePageServiceImpl extends BasePageServiceAbstract<EmployeeEntity> implements EmployeePageService {

    private final EmployeePageDao employeePageDao;

    @Autowired
    public EmployeePageServiceImpl(EmployeePageDao employeePageDao) {
        super(employeePageDao);
        this.employeePageDao = employeePageDao;
    }


}
