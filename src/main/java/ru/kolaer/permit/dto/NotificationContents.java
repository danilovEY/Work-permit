package ru.kolaer.permit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by danilovey on 20.06.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationContents {
    private List<NotificationDto> notifications;
    private Long count;
}
