package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    public EmployeeEntity(Long id, String initials, Date birthday, Long personnelNumber,
                          String email, String workPhone, String gender, String username, String password, DepartmentEntity department,
                          PostEntity post) {
        this.id = id;
        this.initials = initials;
        this.birthday = birthday;
        this.personnelNumber = personnelNumber;
        this.email = email;
        this.workPhone = workPhone;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.department = department;
        this.post = post;
    }

    @Column(name = "initials", length = 100, nullable = false)
    private String initials;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "personnel_number", unique = true, nullable = false)
    private Long personnelNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "work_phone", length = 100)
    private String workPhone;

    @Column(name = "gender", length = 7)
    private String gender;

    @Column(name = "username", unique = true, nullable = false,length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}
