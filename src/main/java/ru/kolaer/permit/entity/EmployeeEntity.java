package ru.kolaer.permit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity extends BaseEntity {

    @Column(name = "initials", length = 100, nullable = false)
    private String initials;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "personnel_number", length = 10, unique = true)
    private Integer personnelNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "work_home", length = 100)
    private String workPhone;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}
