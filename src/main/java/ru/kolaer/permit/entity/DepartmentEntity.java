package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class DepartmentEntity extends BaseEntity {

    public DepartmentEntity(Integer id, String name, String abbreviatedName, List<EmployeeEntity> employees) {
        this.id = id;
        this.name = name;
        this.abbreviatedName = abbreviatedName;
        this.employees = employees;
    }

    @Column(name = "name", nullable = false, unique = true)
    private String name = "";

    @Column(name = "abbreviated_name", length = 100)
    private String abbreviatedName = "";

    @OneToMany(mappedBy = "department")
    private List<EmployeeEntity> employees = new ArrayList<>();

}
