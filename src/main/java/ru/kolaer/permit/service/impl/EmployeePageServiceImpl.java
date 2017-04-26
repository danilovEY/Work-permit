package ru.kolaer.permit.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.EmployeePageService;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Service
public class EmployeePageServiceImpl extends BasePageServiceAbstract<EmployeeEntity> implements EmployeePageService {

    private final EmployeePageDao employeePageDao;

    @Autowired
    public EmployeePageServiceImpl(EmployeePageDao employeePageDao) {
        super(employeePageDao);
        this.employeePageDao = employeePageDao;
    }

    @Override
    public Page<EmployeeEntity> getAll(@NonNull Integer number, @NonNull Integer pageSize) {
        return this.employeePageDao.findAll(number, pageSize, false);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return this.employeePageDao.findAll(false);
    }

    @Override
    public EmployeeEntity getById(@NonNull Integer id) {
        return this.employeePageDao.findById(id, false);
    }

}
