package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by danilovey on 18.04.2017.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    protected Integer id;
}
