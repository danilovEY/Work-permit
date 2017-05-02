package ru.kolaer.permit.service;

import ru.kolaer.permit.entity.FullRoleEntity;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface FullRolePageService extends BasePageService<FullRoleEntity> {
    List<FullRoleEntity> getRolesByIdEmployee(Integer idEmp);
}
