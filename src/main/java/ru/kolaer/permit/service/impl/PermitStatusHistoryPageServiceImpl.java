package ru.kolaer.permit.service.impl;

import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.PermitStatusHistoryPageDao;
import ru.kolaer.permit.entity.PermitStatusHistoryEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.PermitStatusHistoryPageService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Danilov on 27.05.2017.
 */
@Service
public class PermitStatusHistoryPageServiceImpl extends BasePageServiceAbstract<PermitStatusHistoryEntity> implements PermitStatusHistoryPageService {


    private final PermitStatusHistoryPageDao dao;

    public PermitStatusHistoryPageServiceImpl(PermitStatusHistoryPageDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public PermitStatusHistoryEntity getLastStatusByIdPermit(Long id) {
        return this.dao.findLastStatusByIdPermit(id);
    }

    @Override
    public List<PermitStatusHistoryEntity> getLastStatusByIdPermitRange(List<Long> id) {
        if(id == null || id.isEmpty())
            return Collections.emptyList();

        return this.dao.findLastStatusByIdPermitRange(id);
    }

    @Override
    public List<PermitStatusHistoryEntity> getAllByPermitId(Long id) {
        return this.dao.findAllById(id);
    }
}
