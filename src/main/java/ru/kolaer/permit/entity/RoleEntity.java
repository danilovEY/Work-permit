package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity extends BaseEntity {

    public RoleEntity(Integer id, Integer employeeId, String role) {
        this.id = id;
        this.employeeId = employeeId;
        this.role = role;
    }

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "role", length = 20, nullable = false)
    private String role;
}
