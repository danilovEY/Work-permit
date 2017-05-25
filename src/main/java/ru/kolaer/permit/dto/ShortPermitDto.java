package ru.kolaer.permit.dto;

import lombok.*;
import ru.kolaer.permit.entity.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by danilovey on 04.05.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShortPermitDto extends BaseEntity {
    private String serialNumber;

    /**Руководитель*/
    private EmployeeEntity ResponsibleSupervisor;

    /**Исполнитель*/
    private EmployeeEntity ResponsibleExecutor;

    private DepartmentEntity department;

    /**Наименование работ*/
    private String name;

    /**Начало работ*/
    private LocalDateTime startWork;

    /**Конец работ*/
    private LocalDateTime endWork;

    private StatusPermit status;

    /**Кто выдал наряд*/
    private EmployeeEntity writer;
}
