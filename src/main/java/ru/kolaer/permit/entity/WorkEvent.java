package ru.kolaer.permit.entity;

import lombok.*;
import ru.kolaer.permit.TypeEvent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Danilov on 24.05.2017.
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "work_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkEvent extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "work_event_employee",
            joinColumns = @JoinColumn( name="event_id"),
            inverseJoinColumns = @JoinColumn( name="employee_id"))
    private List<EmployeeEntity> employeesEntity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "permit_id")
    private PermitEntity permit;

}
