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
public class PermitEntity extends BaseEntity {
    @Column(name = "serial_number", length = 20, nullable = false, unique = true)
    private String serialNumber;

    /**Руководитель*/
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private EmployeeEntity ResponsibleSupervisor;

    /**Исполнитель*/
    @ManyToOne
    @JoinColumn(name = "executor_id")
    private EmployeeEntity ResponsibleExecutor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "executors_permit",
            joinColumns = @JoinColumn( name="permit_id"),
            inverseJoinColumns = @JoinColumn( name="executor_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EmployeeEntity> executors;

    /**Выдан*/
    @Column(name = "date_write_permit",nullable = false)
    private Date dateWritePermit;

    /**Годен до*/
    @Column(name = "date_limit_permit")
    private Date dateLimitPermit;

    /**Наименование работ*/
    @Column(name = "name", nullable = false)
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

    /**Мероприятия*/
    @OneToMany(mappedBy = "permit", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<WorkEvent> workEvents;

    @Column(length = 100, nullable = false)
    private String status;

    /**Разрешения на подготовку места*/
    /*@OneToMany
    @JoinTable(name = "executors_prepared",
            joinColumns = @JoinColumn( name="prepared_id"),
            inverseJoinColumns = @JoinColumn( name="executor_id"))
    private List<EmployeeEntity> preparedExecutors;*/

    @OneToMany(mappedBy = "permit", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PermitStatusHistoryEntity> permitStatusHistories;

    /**Кто выдал наряд*/
    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private EmployeeEntity writer;

    /**Продление работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "extended_permit")
    private Date extendedPermit;

}
