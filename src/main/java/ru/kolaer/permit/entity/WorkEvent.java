package ru.kolaer.permit.entity;

import lombok.*;
import ru.kolaer.permit.TypeEvent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Column
    private String name;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitDate;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "permit_id")
    private PermitEntity permit;

}
