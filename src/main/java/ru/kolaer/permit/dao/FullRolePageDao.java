package ru.kolaer.permit.dao;

import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.entity.FullRoleEntity;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface FullRolePageDao extends BasePageDao<FullRoleEntity> {

    List<FullRoleEntity> findRolesByIdEmployee(Integer idEmp);

    @Override
    default FullRoleEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_FULL_ROLE;
    }
}
