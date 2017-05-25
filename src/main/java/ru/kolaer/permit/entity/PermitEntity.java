package ru.kolaer.permit.entity;

import lombok.*;

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
public class PermitEntity extends BaseEntity {
    @Column(length = 20)
    private String serialNumber;

    /**Руководитель*/
    @OneToOne
    @JoinColumn(name = "supervisor_id", nullable = false)
    private EmployeeEntity ResponsibleSupervisor;

    /**Исполнитель*/
    @OneToOne
    @JoinColumn(name = "executor_id", nullable = false)
    private EmployeeEntity ResponsibleExecutor;

    @OneToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @OneToMany
    @JoinTable(name = "executors_permit",
            joinColumns = @JoinColumn( name="permit_id"),
            inverseJoinColumns = @JoinColumn( name="executor_id"))
    private List<EmployeeEntity> executors;

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

    /**Условие работ*/
    @Column
    private String conditionWork;

    /**Опасные факторы*/
    @Column
    private String dangerousFactors;

    /**Системы обеспечения безопасности - BEGIN*/

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

    /**Системы обеспечения безопасности - END*/

    /**Материалы*/
    @Column
    private String materials;

    /**Интсрументы*/
    @Column
    private String instruments;

    /**Приспособления */
    @Column
    private String adaptations;

    /**Мероприятия*/
    @OneToMany(mappedBy = "permit", cascade = CascadeType.MERGE)
    private List<WorkEvent> workEvents;

    /**Разрешения на подготовку места*/
    /*@OneToMany
    @JoinTable(name = "executors_prepared",
            joinColumns = @JoinColumn( name="prepared_id"),
            inverseJoinColumns = @JoinColumn( name="executor_id"))
    private List<EmployeeEntity> preparedExecutors;*/

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPermit status;

    /**Кто выдал наряд*/
    @OneToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private EmployeeEntity writer;

    /**Продление работ*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date extendedPermit;

}
