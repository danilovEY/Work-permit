package ru.kolaer.permit.dao;

import ru.kolaer.permit.entity.NotificationEntity;

import java.util.List;

/**
 * Created by danilovey on 20.06.2017.
 */
public interface NotificationPageDao extends BasePageDao<NotificationEntity> {
    List<NotificationEntity> findNotReadableNotifyToEmployeeId(long employeeId);
}
