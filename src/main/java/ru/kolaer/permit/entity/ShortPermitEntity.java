package ru.kolaer.permit.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;

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
@Immutable
public class ShortPermitEntity extends BaseEntity {
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    /**Руководитель*/
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private EmployeeEntity ResponsibleSupervisor;

    /**Исполнитель*/
    @ManyToOne
    @JoinColumn(name = "executor_id")
    private EmployeeEntity ResponsibleExecutor;

    /**Выдан*/
    @Column(name = "date_write_permit", nullable = false)
    private Date dateWritePermit;

    /**Годен до*/
    @Column(name = "date_limit_permit")
    private Date dateLimitPermit;

    /**Наименование работ*/
    @Column(nullable = false)
    private String name;

    /**Начало работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_work")
    private Date startWork;

    /**Конец работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_work")
    private Date endWork;

    /**Место работ*/
    @Column(name = "place_work")
    private String placeWork;

    /**Содержимое работ*/
    @Column(name = "content_work")
    private String contentWork;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    /**Кто выдал наряд*/
    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private EmployeeEntity writer;

    /**Продление работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "extended_permit")
    private Date extendedPermit;

    @Column(name = "complete", nullable = false)
    private boolean complete;

}
