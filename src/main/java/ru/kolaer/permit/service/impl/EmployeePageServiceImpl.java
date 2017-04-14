package ru.kolaer.permit.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.service.EmployeePageService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danilovey on 14.04.2017.
 */
@Service
public class EmployeePageServiceImpl implements EmployeePageService {

    private final EmployeePageDao employeePageDao;

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
        return entity.isEmpty()
                ? entity
                : this.employeePageDao.persistAll(entity);

    }

    @Override
    public EmployeeEntity saveOrUpdate(@NonNull EmployeeEntity entity) {
        return entity.getId() == null ? this.add(entity) : this.update(entity);
    }

    @Override
    public List<EmployeeEntity> saveOrUpdateAll(@NonNull List<EmployeeEntity> entity) {
        final List<EmployeeEntity> withIdEmployees = entity.stream()
                .filter(e -> e.getId() != null)
                .collect(Collectors.toList());

        final List<EmployeeEntity> withoutIdEmployees = entity.stream()
                .filter(e -> e.getId() == null)
                .collect(Collectors.toList());

        this.updateAll(withIdEmployees);
        this.addAll(withoutIdEmployees);

        return entity;
    }

    @Override
    public EmployeeEntity update(@NonNull EmployeeEntity entity) {
        return this.employeePageDao.update(entity);
    }

    @Override
    public List<EmployeeEntity> updateAll(@NonNull List<EmployeeEntity> entity) {
        return entity.isEmpty()
                ? entity
                : this.employeePageDao.updateAll(entity);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return this.employeePageDao.findAll();
    }

    @Override
    public EmployeeEntity getById(@NonNull Integer id) {
        return id < 1
                ? EmptyObjects.DEFAULT_EMPLOYEE
                : this.employeePageDao.findById(id);
    }

    @Override
    public Page<EmployeeEntity> getAll(@NonNull Integer number, @NonNull Integer pageSize) {
        return this.employeePageDao.findAll(number, pageSize);
    }
}
