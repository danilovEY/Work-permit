package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by danilovey on 18.04.2017.
 */
@Getter
@Setter
@MappedSuperclass
@ToString
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    protected Long id;

    @Column(name = "removed", nullable = false)
    private Boolean removed = Boolean.FALSE;
}
