package ru.kolaer.permit.service.impl;

import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.DepartmentPageDao;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.DepartmentPageService;

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
}
