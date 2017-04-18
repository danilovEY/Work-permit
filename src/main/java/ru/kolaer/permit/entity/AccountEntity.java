package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity extends BaseEntity {

    public AccountEntity(Integer id, String username, String password, List<RoleEntity> roles, EmployeeEntity employee) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.employee = employee;
    }

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username = "";

    @Column(name = "password", nullable = false, length = 100)
    private String password = "";

    @OneToMany
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

}
