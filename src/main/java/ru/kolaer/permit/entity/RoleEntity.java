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
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends BaseEntity {

    @Column(name = "role", length = 20, unique = true)
    private String role;
}
