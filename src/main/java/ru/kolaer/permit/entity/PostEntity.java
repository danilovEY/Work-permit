package ru.kolaer.permit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class PostEntity extends BaseEntity {

    public PostEntity(Integer id, String name, String abbreviatedName, Integer rang, String typeRang, List<EmployeeEntity> employees) {
        this.id = id;
        this.name = name;
        this.abbreviatedName = abbreviatedName;
        this.rang = rang;
        this.typeRang = typeRang;
        this.employees = employees;
    }

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "abbreviated_name", length = 100)
    private String abbreviatedName = "";

    @Column(name = "rang")
    private Integer rang;

    @Column(name = "type_rang", length = 10)
    private String typeRang;

    @OneToMany(mappedBy = "post")
    private List<EmployeeEntity> employees = new ArrayList<>();
}
