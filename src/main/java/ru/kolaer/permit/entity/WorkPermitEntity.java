package ru.kolaer.permit.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by danilovey on 04.05.2017.
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "permit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Immutable
public class WorkPermitEntity extends BaseEntity {
    @Column(name = "serial_number", length = 20, nullable = false)
    private String serialNumber;

    /**Выдан*/
    @Column(name = "date_write_permit")
    private Date dateWritePermit;

    /**Годен до*/
    @Column(name = "date_limit_permit")
    private Date dateLimitPermit;

    /**Наименование работ*/
    @Column(name = "name", nullable = false)
    private String name;

    /**Начало работ*/
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_work")
    private Date startWork;

    /**Конец работ*/
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_work")
    private Date endWork;

    /**Место работ*/
    @Column(name = "place_work")
    private String placeWork;

    @Column(length = 100, nullable = false)
    private String status;

    /**Содержимое работ*/
    @Column(name = "content_work")
    private String contentWork;

    /**Условие работ*/
    @Column(name = "condition_work")
    private String conditionWork;

    //Системы обеспечения безопасности - BEGIN

    /**Удерживающие системы*/
    @Column(name = "retaining")
    private String retaining;

    /**Системы позиционирования*/
    @Column(name = "position")
    private String position;

    /**Страховочные системы*/
    @Column(name = "safety")
    private String safety;

    /**Эвакуационные и спасательные системы*/
    @Column(name = "rescue")
    private String rescue;

    //Системы обеспечения безопасности - END

    /**Материалы*/
    @Column(name = "materials")
    private String materials;

    /**Интсрументы*/
    @Column(name = "instruments")
    private String instruments;

    /**Приспособления */
    @Column(name = "adaptations")
    private String adaptations;

    /**Продление работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "extended_permit")
    private Date extendedPermit;

}
