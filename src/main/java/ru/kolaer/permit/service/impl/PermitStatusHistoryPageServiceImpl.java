package ru.kolaer.permit.service.impl;

import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.PermitStatusHistoryPageDao;
import ru.kolaer.permit.entity.PermitStatusHistoryEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.PermitStatusHistoryPageService;

import java.util.List;

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
    public PermitStatusHistoryEntity getLastStatusByIdPermit(Integer id) {
        return this.dao.findLastStatusByIdPermit(id);
    }

    @Override
    public List<PermitStatusHistoryEntity> getLastStatusByIdPermitRange(List<Integer> id) {
        return this.dao.findLastStatusByIdPermitRange(id);
    }
}
