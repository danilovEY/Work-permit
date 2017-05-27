package ru.kolaer.permit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Danilov on 27.05.2017.
 */
@Entity
@Table(name = "permit_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class PermitStatusHistoryEntity extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPermit status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    /**Дата статуса*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date statusDate;

    @Column(name = "permit_id", insertable=false, updatable=false)
    private Integer permitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permit_id")
    private PermitEntity permit;

}
