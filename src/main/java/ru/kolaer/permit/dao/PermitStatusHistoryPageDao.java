package ru.kolaer.permit.dao;

import ru.kolaer.permit.entity.PermitStatusHistoryEntity;

import java.util.List;

/**
 * Created by Danilov on 27.05.2017.
 */
public interface PermitStatusHistoryPageDao extends BasePageDao<PermitStatusHistoryEntity> {
    PermitStatusHistoryEntity findLastStatusByIdPermit(Integer id);

    List<PermitStatusHistoryEntity> findLastStatusByIdPermitRange(List<Integer> id);
}
