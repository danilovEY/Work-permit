package ru.kolaer.permit.service.impl;

import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.PermitPageService;

import java.util.stream.Collectors;

/**
 * Created by Danilov on 24.05.2017.
 */
@Service
public class PermitPageServiceImpl extends BasePageServiceAbstract<PermitEntity> implements PermitPageService {

    private final PermitPageDao dao;

    public PermitPageServiceImpl(PermitPageDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize) {
        final Page<ShortPermitEntity> all = this.dao.findShortAll(number, pageSize, false);

        return all;
    }

    @Override
    public WorkPermitEntity getWorkById(Integer id) {
        return this.dao.findWorkById(id);
    }

    @Override
    public EventPermitEntity getEventById(Integer id) {
        return this.dao.findEventById(id);
    }

    @Override
    public PeoplePermitEntity getPeopleById(Integer id) {
        return this.dao.findPeopleById(id);
    }

    @Override
    public WorkPermitEntity update(WorkPermitEntity workPermitEntity) {
        return this.dao.update(workPermitEntity);
    }

}
