package ru.kolaer.permit.dao;

import ru.kolaer.permit.entity.PermitStatusHistoryEntity;

import java.util.List;

/**
 * Created by Danilov on 27.05.2017.
 */
public interface PermitStatusHistoryPageDao extends BasePageDao<PermitStatusHistoryEntity> {
    PermitStatusHistoryEntity findLastStatusByIdPermit(Long id);

    List<PermitStatusHistoryEntity> findLastStatusByIdPermitRange(List<Long> id);

    List<PermitStatusHistoryEntity> findAllById(Long id);
}
