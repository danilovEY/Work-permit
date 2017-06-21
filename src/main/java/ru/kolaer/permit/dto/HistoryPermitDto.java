package ru.kolaer.permit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by danilovey on 21.06.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryPermitDto {
    private Long id;
    private String serialNumber;
    private String name;
    private String status;
    private Date extendedPermit;
}
