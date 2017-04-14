package ru.kolaer.permit.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.service.EmployeePageService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Service
public class EmployeePageServiceImpl implements EmployeePageService {

    private final EmployeePageDao employeePageDao;

    @PostConstruct
    private void init() {

    }

    @Autowired
    public EmployeePageServiceImpl(EmployeePageDao employeePageDao) {
        this.employeePageDao = employeePageDao;
    }

    @Override
    public EmployeeEntity add(@NonNull EmployeeEntity entity) {
        return this.employeePageDao.persist(entity);
    }

    @Override
    public List<EmployeeEntity> addAll(@NonNull List<EmployeeEntity> entity) {
        return this.employeePageDao.persistAll(entity);
    }

    @Override
    public EmployeeEntity saveOrUpdate(@NonNull EmployeeEntity entity) {
        return null;
    }

    @Override
    public List<EmployeeEntity> saveOrUpdateAll(@NonNull List<EmployeeEntity> entity) {
        return null;
    }

    @Override
    public EmployeeEntity update(@NonNull EmployeeEntity entity) {
        return this.employeePageDao.update(entity);
    }

    @Override
    public List<EmployeeEntity> updateAll(@NonNull List<EmployeeEntity> entity) {
        return this.employeePageDao.updateAll(entity);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return this.employeePageDao.findAll();
    }

    @Override
    public EmployeeEntity getById(@NonNull Integer id) {
        return this.employeePageDao.findById(id);
    }

    @Override
    public Page<EmployeeEntity> getAll(@NonNull Integer number, @NonNull Integer pageSize) {
        return null;
    }
}
