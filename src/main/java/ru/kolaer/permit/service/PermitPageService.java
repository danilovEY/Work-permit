package ru.kolaer.permit.service;

import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;

/**
 * Created by Danilov on 24.05.2017.
 */
public interface PermitPageService extends BasePageService<PermitEntity> {
    Page<ShortPermitEntity> getShortAll(Integer number, Integer pageSize);

    WorkPermitEntity getWorkById(Integer id);
    EventPermitEntity getEventById(Integer id);
    PeoplePermitEntity getPeopleById(Integer id);

    WorkPermitEntity update(WorkPermitEntity workPermitEntity);
    PeoplePermitEntity update(PeoplePermitEntity peoplePermitEntity);
    EventPermitEntity update(EventPermitEntity eventPermitEntity);

    void deleteExecutor(Integer id, Integer executor);
}
