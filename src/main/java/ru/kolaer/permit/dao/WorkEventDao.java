package ru.kolaer.permit.dao;

import ru.kolaer.permit.entity.WorkEvent;

import java.util.List;

/**
 * Created by Danilov on 01.06.2017.
 */
public interface WorkEventDao extends BasePageDao<WorkEvent> {
    List<WorkEvent> findByIdPermit(Integer idPermit);

    boolean deleteEmployee(Integer idWorkEvent, Integer idExecuter);
}
