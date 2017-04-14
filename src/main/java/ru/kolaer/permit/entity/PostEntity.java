package ru.kolaer.permit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "abbreviated_name", length = 100)
    private String abbreviatedName;

    @Column(name = "rang")
    private Integer rang;

    @Column(name = "type_rang", length = 10)
    private String typeRang;

    @OneToMany(mappedBy = "post")
    private List<EmployeeEntity> employees;
}
