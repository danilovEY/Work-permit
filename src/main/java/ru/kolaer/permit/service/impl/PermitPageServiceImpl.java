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

    @Override
    public PeoplePermitEntity update(PeoplePermitEntity peoplePermitEntity) {
        return this.dao.update(peoplePermitEntity);
    }

    @Override
    public EventPermitEntity update(EventPermitEntity eventPermitEntity) {
        return this.dao.update(eventPermitEntity);
    }

    @Override
    public void deleteExecutor(Integer id, Integer executor) {
        this.dao.deleteExecutor(id, executor);
    }

    @Override
    public PermitEntity delete(PermitEntity entity) {
        return this.dao.delete(entity, true);
    }
}
