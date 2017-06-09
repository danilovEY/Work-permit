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

    /**Кто выдал наряд*/
    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private EmployeeEntity writer;

    /**Наряд получил*/
    @ManyToOne
    @JoinColumn(name = "accepted_id")
    private EmployeeEntity accepted;

    /**Наряд иструктаж провел*/
    @ManyToOne
    @JoinColumn(name = "briefing_held_id")
    private EmployeeEntity briefingHeld;

    /**Наряд инструктаж получил*/
    @ManyToOne
    @JoinColumn(name = "briefing_accept_id")
    private EmployeeEntity briefingAccept;

}
