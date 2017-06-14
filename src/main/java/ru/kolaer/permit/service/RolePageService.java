package ru.kolaer.permit.service;

import lombok.NonNull;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface RolePageService extends BasePageService<RoleEntity> {
    List<RoleEntity> getRolesByIdEmployee(Integer idEmp);
    Page<RoleEntity> getAll(@NonNull Integer number, @NonNull Integer pageSize, Integer sort);
}
