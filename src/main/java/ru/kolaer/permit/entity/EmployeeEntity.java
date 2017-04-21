package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "employees")
@Setter
@Getter
@NoArgsConstructor
public class EmployeeEntity extends BaseEntity {

    public EmployeeEntity(Integer id, String initials, Date birthday, Integer personnelNumber,
                          String email, String workPhone, DepartmentEntity department,
                          PostEntity post) {
        this.id = id;
        this.initials = initials;
        this.birthday = birthday;
        this.personnelNumber = personnelNumber;
        this.email = email;
        this.workPhone = workPhone;
        this.department = department;
        this.post = post;
    }

    @Column(name = "initials", length = 100, nullable = false)
    private String initials;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "personnel_number", length = 10,
            unique = true, nullable = false)
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
