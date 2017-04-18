package ru.kolaer.permit.dao;

import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.entity.DepartmentEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface DepartmentPageDao extends BasePageDao<DepartmentEntity> {

    @Override
    default DepartmentEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_DEPARTMENT;
    }
}
