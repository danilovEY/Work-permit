package ru.kolaer.permit.service;

import ru.kolaer.permit.entity.NotificationEntity;

import java.util.List;

/**
 * Created by danilovey on 20.06.2017.
 */
public interface NotificationPageService extends BasePageService<NotificationEntity> {
    List<NotificationEntity> getNotReadableNotification(long empId);
}
