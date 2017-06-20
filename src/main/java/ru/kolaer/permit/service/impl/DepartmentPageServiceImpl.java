package ru.kolaer.permit.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.DepartmentPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.DepartmentPageService;

import java.util.List;

/**
 * Created by danilovey on 21.04.2017.
 */
@Service
public class DepartmentPageServiceImpl extends BasePageServiceAbstract<DepartmentEntity> implements DepartmentPageService {
    private final DepartmentPageDao departmentPageDao;

    public DepartmentPageServiceImpl(DepartmentPageDao departmentPageDao) {
        super(departmentPageDao);
        this.departmentPageDao = departmentPageDao;
    }

    @Override
    public Page<DepartmentEntity> getAll(@NonNull Integer number, @NonNull Integer pageSize) {
        return this.departmentPageDao.findAll(number, pageSize, false);
    }

    @Override
    public List<DepartmentEntity> getAll() {
        return this.departmentPageDao.findAll(false);
    }

    @Override
    public DepartmentEntity getById(@NonNull Long id) {
        return this.departmentPageDao.findById(id, false);
    }

    @Override
    public DepartmentEntity delete(DepartmentEntity entity) {
        return this.departmentPageDao.delete(entity, true);
    }

    @Override
    public List<DepartmentEntity> deleteAll(List<DepartmentEntity> entities) {
        return this.departmentPageDao.deleteAll(entities, true);
    }

    @Override
    public boolean existDepartment(DepartmentEntity department) {
        return this.departmentPageDao.existDepartment(department);
    }
}
