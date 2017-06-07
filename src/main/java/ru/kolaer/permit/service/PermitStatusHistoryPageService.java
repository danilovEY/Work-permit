package ru.kolaer.permit.service;

import ru.kolaer.permit.entity.PermitStatusHistoryEntity;

import java.util.List;

/**
 * Created by Danilov on 27.05.2017.
 */
public interface PermitStatusHistoryPageService extends BasePageService<PermitStatusHistoryEntity> {
    PermitStatusHistoryEntity getLastStatusByIdPermit(Integer id);
    List<PermitStatusHistoryEntity> getLastStatusByIdPermitRange(List<Integer> id);

    List<PermitStatusHistoryEntity> getAllByPermitId(Integer id);
}
