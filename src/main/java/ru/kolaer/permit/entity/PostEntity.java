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
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity extends BaseEntity {

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
