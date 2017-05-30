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
public class PeoplePermitEntity extends BaseEntity {
    @Column(length = 20)
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

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPermit status;

    /**Кто выдал наряд*/
    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private EmployeeEntity writer;

}
