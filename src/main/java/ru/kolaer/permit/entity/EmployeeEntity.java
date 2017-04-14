package ru.kolaer.permit.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "initials", length = 100, nullable = false)
    private String initials;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "personal_number", length = 10)
    private Integer personalNumber;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}
