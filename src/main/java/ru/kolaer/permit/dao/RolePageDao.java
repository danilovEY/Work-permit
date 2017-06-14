package ru.kolaer.permit.dao;

import lombok.NonNull;
import org.apache.poi.ss.formula.functions.T;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface RolePageDao extends BasePageDao<RoleEntity> {

    List<RoleEntity> findRolesByIdEmployee(Integer idEmp);

    @Override
    default RoleEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_FULL_ROLE;
    }

    Page<RoleEntity> findAll(@NonNull Integer number, @NonNull Integer pageSize, boolean findRemoved, Integer sort);

}
