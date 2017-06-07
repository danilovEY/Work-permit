package ru.kolaer.permit.service;

import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;

import java.io.File;

/**
 * Created by Danilov on 24.05.2017.
 */
public interface PermitPageService extends BasePageService<PermitEntity> {
    String EDIT_STATUS = "Редактирование";
    String CANCEL_STATUS = "Отменен";
    String NEED_REQUEST_STATUS = "Запрос на согласование";

    Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize);
    Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize, Integer sort);

    WorkPermitEntity getWorkById(Integer id);
    EventPermitEntity getEventById(Integer id);
    PeoplePermitEntity getPeopleById(Integer id);

    WorkPermitEntity update(WorkPermitEntity workPermitEntity);
    PeoplePermitEntity update(PeoplePermitEntity peoplePermitEntity);
    EventPermitEntity update(EventPermitEntity eventPermitEntity);

    void deleteExecutor(Integer id, Integer executor);

    boolean existSerialNumber(String serialNumber);

    boolean setStatus(Integer id, String status, EmployeeEntity whoSetStatus);

    File printPermitToExcel(Integer id);

    String getSerialNumber(Integer id);

    boolean cancel(Integer id, EmployeeEntity whoSetStatus);
}
