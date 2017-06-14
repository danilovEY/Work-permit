package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity extends BaseEntity {

    public RoleEntity(Integer id, EmployeeEntity employee, String role) {
        this.id = id;
        this.employee = employee;
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @Column(name = "role", length = 20)
    private String role;
}
