package ru.kolaer.permit.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
public class ShortPermitEntity extends BaseEntity {
    @Column(length = 20)
    private String serialNumber;

    /**Руководитель*/
    @ManyToOne
    @JoinColumn(name = "supervisor_id", nullable = false)
    private EmployeeEntity ResponsibleSupervisor;

    /**Исполнитель*/
    @ManyToOne
    @JoinColumn(name = "executor_id", nullable = false)
    private EmployeeEntity ResponsibleExecutor;

    /**Выдан*/
    @Column(name = "write_word")
    private Date dateWritePermit;

    /**Годен до*/
    @Column(name = "limit_word")
    private Date dateLimitPermit;

    /**Наименование работ*/
    @Column
    private String name;

    /**Начало работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date startWork;

    /**Конец работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date endWork;

    /**Место работ*/
    @Column
    private String placeWork;

    /**Содержимое работ*/
    @Column
    private String contentWork;

    @Column(length = 100, nullable = false)
    private String status;

    /**Кто выдал наряд*/
    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private EmployeeEntity writer;

    /**Продление работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date extendedPermit;

}
