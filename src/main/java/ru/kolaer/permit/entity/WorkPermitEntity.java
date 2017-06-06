package ru.kolaer.permit.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
public class WorkPermitEntity extends BaseEntity {
    @Column(length = 20)
    private String serialNumber;

    /**Выдан*/
    @Column(name = "write_word")
    private Date dateWritePermit;

    /**Годен до*/
    @Column(name = "limit_word")
    private Date dateLimitPermit;

    /**Наименование работ*/
    @Column
    private String name;

    /**Комметы*/
    @Column
    private String comment;

    /**Начало работ*/
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date startWork;

    /**Конец работ*/
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date endWork;

    /**Место работ*/
    @Column
    private String placeWork;

    /**Содержимое работ*/
    @Column
    private String contentWork;

    /**Условие работ*/
    @Column
    private String conditionWork;

    /**Опасные факторы*/
    @Column
    private String dangerousFactors;

    //Системы обеспечения безопасности - BEGIN

    /**Удерживающие системы*/
    @Column
    private String retaining;

    /**Системы позиционирования*/
    @Column
    private String position;

    /**Страховочные системы*/
    @Column
    private String safety;

    /**Эвакуационные и спасательные системы*/
    @Column
    private String rescue;

    //Системы обеспечения безопасности - END

    /**Материалы*/
    @Column
    private String materials;

    /**Интсрументы*/
    @Column
    private String instruments;

    /**Приспособления */
    @Column
    private String adaptations;

    /**Продление работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date extendedPermit;

}
