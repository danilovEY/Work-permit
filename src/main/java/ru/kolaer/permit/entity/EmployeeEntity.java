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
                          String email, String workPhone, String username, String password, DepartmentEntity department,
                          PostEntity post) {
        this.id = id;
        this.initials = initials;
        this.birthday = birthday;
        this.personnelNumber = personnelNumber;
        this.email = email;
        this.workPhone = workPhone;
        this.username = username;
        this.password = password;
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

    @Column(name = "username", unique = true, nullable = false,length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "removed", nullable = false)
    private Boolean removed;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}
