package ru.kolaer.permit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name = "";

    @Column(name = "abbreviated_name", length = 100)
    private String abbreviatedName = "";

    @OneToMany(mappedBy = "department")
    private List<EmployeeEntity> employees = new ArrayList<>();

}
