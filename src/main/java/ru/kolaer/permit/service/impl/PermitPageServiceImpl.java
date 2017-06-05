package ru.kolaer.permit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.PermitPageService;

import java.io.File;
import java.util.stream.Collectors;

/**
 * Created by Danilov on 24.05.2017.
 */
@Service
@Slf4j
public class PermitPageServiceImpl extends BasePageServiceAbstract<PermitEntity> implements PermitPageService {

    private final String pathTemplatePermit;
    private final PermitPageDao dao;

    public PermitPageServiceImpl(@Value("${permit.template.path}") String pathTemplatePermit, PermitPageDao dao) {
        super(dao);
        this.pathTemplatePermit = pathTemplatePermit;
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
    public boolean existSerialNumber(String serialNumber) {
        return this.dao.existSerialNumber(serialNumber);
    }

    @Override
    public void printPermitToExcel(Integer id) {
        final PermitEntity printPermit = this.dao.findById(id, true);

        final File fileTemplate = new File(System.getProperty("permit.home") + "/" + pathTemplatePermit);

        log.debug(fileTemplate.getAbsolutePath());

        if(!fileTemplate.exists()) {
            log.error("File template is not exist!");
        }


    }

    @Override
    public PermitEntity delete(PermitEntity entity) {
        return this.dao.delete(entity, true);
    }
}
