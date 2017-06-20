package ru.kolaer.permit.dto;

import lombok.*;

import java.util.Date;

/**
 * Created by danilovey on 20.06.2017.
 */
@Data
public class NotificationDto {
    private Long id;
    private boolean read;
    private Long toId;
    private Date createDate;
    private String dateString;
    private NotificationType type;
    private Long eventFromId;
    private String message;

}
