package ru.kolaer.permit.dao;

import ru.kolaer.permit.dto.HistoryPermitDto;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;

import java.util.List;

/**
 * Created by Danilov on 24.05.2017.
 */
public interface PermitPageDao extends BasePageDao<PermitEntity> {
    Page<ShortPermitEntity> findShortAll(Integer number, Integer pageSize, Integer sort, boolean includeRemoved);
    Page<ShortPermitEntity> findShortAll(Integer number, Integer pageSize, Integer sort, boolean includeRemoved, String search);

    ShortPermitEntity findShortById(Long id);
    WorkPermitEntity findWorkById(Long id);
    EventPermitEntity findEventById(Long id);
    PeoplePermitEntity findPeopleById(Long id);

    WorkPermitEntity update(WorkPermitEntity workPermitEntity);
    PeoplePermitEntity update(PeoplePermitEntity peoplePermitEntity);
    EventPermitEntity update(EventPermitEntity eventPermitEntity);

    void deleteExecutor(Long id, Long executor);

    boolean existSerialNumber(String serialNumber);

    boolean setStatus(Long id, String status);
    boolean setStatus(List<Long> ids, String status);

    String findSerialNumberById(Long id);

    List<Long> findAllByStatusAndOverdue(String overdue);

    List<Long> findAllByStatusAndStartWork(String status);

    boolean setCompletePermit(Long id, boolean b);

    HistoryPermitDto findHistoryPermitDtoById(Long id);
}
