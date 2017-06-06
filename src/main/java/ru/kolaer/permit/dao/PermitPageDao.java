package ru.kolaer.permit.dao;

import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;

/**
 * Created by Danilov on 24.05.2017.
 */
public interface PermitPageDao extends BasePageDao<PermitEntity> {
    Page<ShortPermitEntity> findShortAll(Integer number, Integer pageSize, boolean includeRemoved);

    WorkPermitEntity findWorkById(Integer id);
    EventPermitEntity findEventById(Integer id);
    PeoplePermitEntity findPeopleById(Integer id);

    WorkPermitEntity update(WorkPermitEntity workPermitEntity);
    PeoplePermitEntity update(PeoplePermitEntity peoplePermitEntity);
    EventPermitEntity update(EventPermitEntity eventPermitEntity);

    void deleteExecutor(Integer id, Integer executor);

    boolean existSerialNumber(String serialNumber);

    boolean setStatus(Integer id, String status);
}
