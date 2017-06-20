package ru.kolaer.permit.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by danilovey on 16.06.2017.
 */
@Data
public class ExtendedPermitDto {
    private Long id;
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private Date extendedPermit;
}
