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

    public RoleEntity(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    @Column(name = "role", length = 20, unique = true)
    private String role;
}
