package ru.kolaer.permit.service.impl;

import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.NotificationPageDao;
import ru.kolaer.permit.dto.NotificationContents;
import ru.kolaer.permit.dto.NotificationDto;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.NotificationEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.NotificationPageService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danilovey on 20.06.2017.
 */
@Service
public class NotificationPageServiceImpl extends BasePageServiceAbstract<NotificationEntity> implements NotificationPageService {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final NotificationPageDao notifyDao;

    public NotificationPageServiceImpl(NotificationPageDao dao) {
        super(dao);
        this.notifyDao = dao;
    }

    @Override
    public List<NotificationEntity> getNotReadableNotification(long empId) {
        return this.notifyDao.findAllNotReadableNotifyToEmployeeId(empId);
    }

    @Override
    public NotificationContents getNotificationContents(long empId) {
        List<NotificationEntity> limitNotify = Collections.emptyList();
        Long count = 0L;

        if(empId > 0) {
            limitNotify = this.notifyDao.findLimitNotReadableNotifyToEmployeeId(empId, 15);
            count = this.notifyDao.findCountNotReadableNotifyToEmployeeId(empId);
        }

        return new NotificationContents(this.convertToDto(limitNotify), count);
    }

    @Override
    public List<NotificationDto> convertToDto(List<NotificationEntity> entities) {
        if(entities == null || entities.isEmpty())
            return Collections.emptyList();

        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto convertToDto(NotificationEntity entity) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(entity.getId());
        notificationDto.setType(entity.getType());
        notificationDto.setCreateDate(entity.getCreateDate());
        notificationDto.setEventFromId(entity.getEventFromId());
        notificationDto.setRead(entity.isRead());
        notificationDto.setMessage(entity.getMessage());
        notificationDto.setToId(entity.getToId());
        notificationDto.setDateString(this.dateToString(entity.getCreateDate()));
        return notificationDto;
    }

    @Override
    public Page<NotificationEntity> getByEmployeeId(Integer number, Integer pageSize, Long id) {
        return this.notifyDao.findAllByEmployeeId(number, pageSize, id);
    }

    private String dateToString(Date date) {
        Calendar sourceDate = Calendar.getInstance();
        sourceDate.setTime(date);

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date());

        int sourceSecond = sourceDate.get(Calendar.SECOND);
        int sourceMin = sourceDate.get(Calendar.MINUTE);
        int sourceHour = sourceDate.get(Calendar.HOUR_OF_DAY);
        int sourceDay = sourceDate.get(Calendar.DAY_OF_MONTH);
        int sourceMonth = sourceDate.get(Calendar.MONTH);
        int sourceYear = sourceDate.get(Calendar.YEAR);

        int nowSecond = nowCalendar.get(Calendar.SECOND);
        int nowMin = nowCalendar.get(Calendar.MINUTE);
        int nowHour = nowCalendar.get(Calendar.HOUR_OF_DAY);
        int nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
        int nowMonth = nowCalendar.get(Calendar.MONTH);
        int nowYear = nowCalendar.get(Calendar.YEAR);

        if(nowSecond >= sourceSecond
                && sourceMin == nowMin
                && sourceHour == nowHour
                && sourceDay == nowDay
                && sourceMonth == nowMonth
                && sourceYear == nowYear) {
            return String.valueOf(nowSecond - sourceSecond) + " сек.";
        }

        if(nowMin >= sourceMin
                && sourceHour == nowHour
                && sourceDay == nowDay
                && sourceMonth == nowMonth
                && sourceYear == nowYear) {
            return String.valueOf(nowMin - sourceMin) + " мин.";
        }

        if(nowHour >= sourceHour
                && sourceDay == nowDay
                && sourceMonth == nowMonth
                && sourceYear == nowYear) {
            int per = nowHour - sourceHour;
            return per == 1
                    ? "Час"
                    : String.valueOf(per) + " часов";
        }

        if(nowDay >= sourceDay
                && sourceMonth == nowMonth
                && sourceYear == nowYear) {
            int per = nowDay - sourceDay;
            return per == 1
                    ? "Вчера"
                    : String.valueOf(per) + " дня";
        }

        return simpleDateFormat.format(date);
    }
}
