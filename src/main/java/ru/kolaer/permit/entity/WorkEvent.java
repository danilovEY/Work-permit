package ru.kolaer.permit.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kolaer.permit.entity.enums.TypeEvent;

import javax.persistence.*;
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

    @Column(name = "name", nullable = false)
    private String name;

    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    @Column(name = "limit_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "work_event_employee",
            joinColumns = @JoinColumn( name="event_id"),
            inverseJoinColumns = @JoinColumn( name="employee_id"))
    private List<EmployeeEntity> employeesEntity;

    @Column(name = "type_event", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;

    @Column(name = "permit_id", nullable = false, insertable=false, updatable=false)
    private Long permitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "permit_id")
    private PermitEntity permit;

}
