package ru.kolaer.permit.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoleEntity extends BaseEntity {

    public RoleEntity(Long id, EmployeeEntity employee, String role) {
        this.id = id;
        this.employee = employee;
        this.role = role;
    }

    @Column(name = "employee_id", nullable = false, updatable = false, insertable = false)
    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @Column(name = "role", length = 20, nullable = false)
    private String role;
}
