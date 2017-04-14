package ru.kolaer.permit.entity;

import javax.persistence.*;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

}
