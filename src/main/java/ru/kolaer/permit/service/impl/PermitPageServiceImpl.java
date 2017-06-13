package ru.kolaer.permit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.PermitPageService;
import ru.kolaer.permit.service.PermitStatusHistoryPageService;

import java.io.File;
import java.util.*;

/**
 * Created by Danilov on 24.05.2017.
 */
@Service
@Slf4j
public class PermitPageServiceImpl extends BasePageServiceAbstract<PermitEntity> implements PermitPageService {

    private final PermitPageDao dao;
    private final PermitConverterExcel permitConverterExcel;
    private final PermitStatusHistoryPageService permitStatusHistoryPageService;

    public PermitPageServiceImpl(PermitPageDao dao,
                                 PermitConverterExcel permitConverterExcel,
                                 PermitStatusHistoryPageService permitStatusHistoryPageService) {
        super(dao);
        this.dao = dao;
        this.permitConverterExcel = permitConverterExcel;
        this.permitStatusHistoryPageService = permitStatusHistoryPageService;
    }

    @Override
    public Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize) {
        return this.getShortAll(number, pageSize, 0);
    }

    @Override
    public Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize, Integer sort) {
        return this.dao.findShortAll(number, pageSize, sort, false);
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
    public WorkPermitEntity update(WorkPermitEntity workPermitEntity, EmployeeEntity whoUpdate) {
        final WorkPermitEntity origin = this.dao.findWorkById(workPermitEntity.getId());
        if(!origin.getEndWork().equals(workPermitEntity.getEndWork())){
            this.setStatus(workPermitEntity.getId(), EXTEND_STATUS, whoUpdate);
        }
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
    public boolean setStatus(Integer id, String status, EmployeeEntity whoSetStatus) {
        final PermitEntity permitEntity = new PermitEntity();
        permitEntity.setId(id);

        final PermitStatusHistoryEntity statusHistory = new PermitStatusHistoryEntity();
        statusHistory.setStatus(status);
        statusHistory.setStatusDate(new Date());
        statusHistory.setEmployee(whoSetStatus);
        statusHistory.setPermitId(permitEntity.getId());
        statusHistory.setPermit(permitEntity);

        this.permitStatusHistoryPageService.add(statusHistory);

        return this.dao.setStatus(id, status);
    }

    @Override
    public File printPermitToExcel(Integer id) {
        return this.permitConverterExcel.convertPermit(id);
    }

    @Override
    public String getSerialNumber(Integer id) {
        return this.dao.findSerialNumberById(id);
    }

    @Override
    public boolean cancel(Integer id, EmployeeEntity whoSetStatus) {
        return this.setStatus(id, CANCELED_STATUS, whoSetStatus);
    }

    @Override
    public PermitEntity delete(PermitEntity permitEntity, EmployeeEntity whoDeleted) {
        return this.setStatus(permitEntity.getId(), DELETED_STATUS, whoDeleted)
                ? this.dao.delete(permitEntity, true)
                : permitEntity;
    }

    @Override
    public PermitEntity add(WorkPermitEntity workPermitEntity, EmployeeEntity whoWrite) {
        final  PermitEntity permitEntity = this.convertWorkPermitToPermit(workPermitEntity);
        permitEntity.setStatus(EDIT_STATUS);

        permitEntity.setWriter(whoWrite);
        permitEntity.setDateWritePermit(new Date());
        permitEntity.setExtendedPermit(permitEntity.getEndWork());


        final PermitStatusHistoryEntity createNewPermit = new PermitStatusHistoryEntity();
        createNewPermit.setEmployee(permitEntity.getWriter());
        createNewPermit.setPermit(permitEntity);
        createNewPermit.setPermitId(permitEntity.getId());
        createNewPermit.setStatusDate(new Date());
        createNewPermit.setStatus(EDIT_STATUS);

        permitEntity.setPermitStatusHistories(Collections.singletonList(createNewPermit));

        return this.add(permitEntity);
    }

    @Override
    public void endPermit(Integer id, EmployeeEntity authEmployee) {
        if(this.dao.setCompletePermit(id, true)) {
            this.setStatus(id, END_STATUS, authEmployee);
        }
    }

    private PermitEntity convertWorkPermitToPermit(WorkPermitEntity workPermitEntity) {
        final  PermitEntity permitEntity = new PermitEntity();
        permitEntity.setSerialNumber(workPermitEntity.getSerialNumber());
        permitEntity.setName(workPermitEntity.getName());
        permitEntity.setPlaceWork(workPermitEntity.getPlaceWork());
        permitEntity.setContentWork(workPermitEntity.getContentWork());
        permitEntity.setConditionWork(workPermitEntity.getConditionWork());
        permitEntity.setStartWork(workPermitEntity.getStartWork());
        permitEntity.setEndWork(workPermitEntity.getEndWork());
        permitEntity.setMaterials(workPermitEntity.getMaterials());
        permitEntity.setInstruments(workPermitEntity.getInstruments());
        permitEntity.setAdaptations(workPermitEntity.getAdaptations());
        permitEntity.setRetaining(workPermitEntity.getRetaining());
        permitEntity.setPosition(workPermitEntity.getPosition());
        permitEntity.setSafety(workPermitEntity.getSafety());
        permitEntity.setRescue(workPermitEntity.getRescue());
        permitEntity.setDateLimitPermit(workPermitEntity.getDateLimitPermit());
        permitEntity.setDateWritePermit(workPermitEntity.getDateWritePermit());

        return permitEntity;
    }

    @Override
    public PermitEntity delete(PermitEntity permitEntity) {
        return this.dao.delete(permitEntity, true);
    }
}
