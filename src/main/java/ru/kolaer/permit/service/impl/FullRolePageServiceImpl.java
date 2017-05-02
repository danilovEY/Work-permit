package ru.kolaer.permit.service.impl;

import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.FullRolePageDao;
import ru.kolaer.permit.entity.FullRoleEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.FullRolePageService;

import java.util.List;

/**
 * Created by danilovey on 24.04.2017.
 */
@Service
public class FullRolePageServiceImpl extends BasePageServiceAbstract<FullRoleEntity> implements FullRolePageService {

    private final FullRolePageDao dao;

    public FullRolePageServiceImpl(FullRolePageDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public List<FullRoleEntity> getRolesByIdEmployee(Integer idEmp) {
        return this.dao.findRolesByIdEmployee(idEmp);
    }
}
