package ru.kolaer.permit.service;

import ru.kolaer.permit.dto.NotificationContents;
import ru.kolaer.permit.dto.NotificationDto;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.NotificationEntity;

import java.util.List;

/**
 * Created by danilovey on 20.06.2017.
 */
public interface NotificationPageService extends BasePageService<NotificationEntity> {
    List<NotificationEntity> getNotReadableNotification(long empId);
    NotificationContents getNotificationContents(long empId);

    List<NotificationDto> convertToDto(List<NotificationEntity> entities);
    NotificationDto convertToDto(NotificationEntity entity);

    Page<NotificationEntity> getByEmployeeId(Integer number, Integer pageSize, Long id);
}
