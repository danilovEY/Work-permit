package ru.kolaer.permit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dao.PermitStatusHistoryPageDao;
import ru.kolaer.permit.entity.PermitEntity;
import ru.kolaer.permit.entity.PermitStatusHistoryEntity;
import ru.kolaer.permit.service.PermitPageService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danilovey on 07.06.2017.
 */
@Service
@Slf4j
public class PermitScheduler {

    private final PermitPageDao permitPageDao;
    private final PermitStatusHistoryPageDao permitStatusHistoryPageDao;

    public PermitScheduler(PermitPageDao permitPageDao, PermitStatusHistoryPageDao permitStatusHistoryPageDao) {
        this.permitPageDao = permitPageDao;
        this.permitStatusHistoryPageDao = permitStatusHistoryPageDao;
    }

    public void autoSetOverduePermit() {
        final List<Integer> permitIds = this.permitPageDao.findAllByStatusAndOverdue(PermitPageService.WORKING_STATUS);

        log.info("Кол-во просроченных нарядов: {}", permitIds.size());

        if(!permitIds.isEmpty() && this.permitPageDao.setStatus(permitIds, PermitPageService.OVERDUE_STATUS)) {
            final List<PermitStatusHistoryEntity> histories = permitIds.stream()
                    .map(id -> this.createPermit(id, PermitPageService.OVERDUE_STATUS))
                    .map(this::createHistory)
                    .collect(Collectors.toList());

            this.permitStatusHistoryPageDao.persistAll(histories);
        }
    }

    public void autoSetWorkPermit() {
        final List<Integer> permitIds = this.permitPageDao.findAllByStatusAndStartWork(PermitPageService.PERMIT_STATUS);

        log.info("Кол-во не начатых нарядов: {}", permitIds.size());

        if(!permitIds.isEmpty() && this.permitPageDao.setStatus(permitIds, PermitPageService.WORKING_STATUS)) {
            final List<PermitStatusHistoryEntity> histories = permitIds.stream()
                    .map(id -> this.createPermit(id, PermitPageService.WORKING_STATUS))
                    .map(this::createHistory)
                    .collect(Collectors.toList());

            this.permitStatusHistoryPageDao.persistAll(histories);
        }
    }

    private PermitEntity createPermit(Integer id, String status) {
        final PermitEntity permitEntity = new PermitEntity();
        permitEntity.setId(id);
        permitEntity.setStatus(status);

        return permitEntity;
    }

    private PermitStatusHistoryEntity createHistory(PermitEntity permitEntity) {
        final PermitStatusHistoryEntity permitStatusHistoryEntity = new PermitStatusHistoryEntity();
        permitStatusHistoryEntity.setPermit(permitEntity);
        permitStatusHistoryEntity.setStatus(permitEntity.getStatus());
        permitStatusHistoryEntity.setPermitId(permitEntity.getId());
        permitStatusHistoryEntity.setStatusDate(new Date());

        return permitStatusHistoryEntity;
    }

}
