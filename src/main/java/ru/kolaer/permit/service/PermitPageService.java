package ru.kolaer.permit.service;

import ru.kolaer.permit.dto.HistoryPermitDto;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;

import java.io.File;
import java.util.Date;

/**
 * Created by Danilov on 24.05.2017.
 */
public interface PermitPageService extends BasePageService<PermitEntity> {
    String EDIT_STATUS = "Редактирование";
    String CANCELED_STATUS = "Отменен";
    String NEED_APPROVE_STATUS = "Запрос на согласование";
    String APPROVE_STATUS = "Согласовано";
    String OVERDUE_STATUS = "Просрочен";
    String WORKING_STATUS = "В работе";
    String PERMIT_STATUS = "Допуск";
    String DELETED_STATUS = "Удален";
    String EXTEND_STATUS = "Продлен";
    String END_STATUS = "Завершен";

    Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize);
    Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize, Integer sort);
    Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize, Integer sort, String search);

    HistoryPermitDto getHistoryPermitDtoId(Long id);
    ShortPermitEntity getShortById(Long id);
    WorkPermitEntity getWorkById(Long id);
    EventPermitEntity getEventById(Long id);
    PeoplePermitEntity getPeopleById(Long id);

    WorkPermitEntity update(WorkPermitEntity workPermitEntity);
    PeoplePermitEntity update(PeoplePermitEntity peoplePermitEntity);
    EventPermitEntity update(EventPermitEntity eventPermitEntity);


    void deleteExecutor(Long id, Long executor);

    boolean extendPermit(Long id, Date extendDate, EmployeeEntity whoExtend);
    boolean existSerialNumber(String serialNumber);

    boolean setStatus(Long id, String status, EmployeeEntity whoSetStatus);

    File printPermitToExcel(Long id);

    String getSerialNumber(Long id);

    boolean cancel(Long id, EmployeeEntity whoSetStatus);

    PermitEntity delete(PermitEntity permitEntity, EmployeeEntity whoDeleted);

    PermitEntity add(WorkPermitEntity workPermitEntity, EmployeeEntity whoWrite);

    void endPermit(Long id, EmployeeEntity authEmployee);
}
