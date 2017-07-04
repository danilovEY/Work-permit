package ru.kolaer.permit.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.RolePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.RoleEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.RolePageService;

import java.util.List;

/**
 * Created by danilovey on 24.04.2017.
 */
@Service
public class RolePageServiceImpl extends BasePageServiceAbstract<RoleEntity> implements RolePageService {

    private final RolePageDao dao;

    public RolePageServiceImpl(RolePageDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public List<RoleEntity> getRolesByIdEmployee(Integer idEmp) {
        return this.dao.findRolesByIdEmployee(idEmp);
    }

    @Override
    public Page<RoleEntity> getAll(@NonNull Integer number, @NonNull Integer pageSize, Integer sort) {
        return this.dao.findAll(number, pageSize, true, sort);
    }

    @Override
    public Page<RoleEntity> getAll(@NonNull Integer number, @NonNull Integer pageSize) {
        return this.dao.findAll(number, pageSize, true, 0);
    }

    @Override
    public RoleEntity delete(RoleEntity entity) {
        return this.dao.delete(entity, false);
    }

    @Override
    public boolean delete(Long idRole) {
        return dao.delete(idRole, false);
    }
}
