package ru.kolaer.permit.dao;

import ru.kolaer.permit.entity.NotificationEntity;

import java.util.List;

/**
 * Created by danilovey on 20.06.2017.
 */
public interface NotificationPageDao extends BasePageDao<NotificationEntity> {
    List<NotificationEntity> findAllNotReadableNotifyToEmployeeId(long employeeId);
    List<NotificationEntity> findLimitNotReadableNotifyToEmployeeId(long employeeId, int limit);
    Long findCountNotReadableNotifyToEmployeeId(long employeeId);
}
