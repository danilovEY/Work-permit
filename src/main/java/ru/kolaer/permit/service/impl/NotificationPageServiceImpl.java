package ru.kolaer.permit.service.impl;

import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.NotificationPageDao;
import ru.kolaer.permit.entity.NotificationEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.NotificationPageService;

import java.util.List;

/**
 * Created by danilovey on 20.06.2017.
 */
@Service
public class NotificationPageServiceImpl extends BasePageServiceAbstract<NotificationEntity> implements NotificationPageService {
    private final NotificationPageDao notifyDao;

    public NotificationPageServiceImpl(NotificationPageDao dao) {
        super(dao);
        this.notifyDao = dao;
    }

    @Override
    public List<NotificationEntity> getNotReadableNotification(long empId) {
        return this.notifyDao.findNotReadableNotifyToEmployeeId(empId);
    }
}
