package ru.kolaer.permit.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by danilovey on 04.05.2017.
 */
@EqualsAndHashCode(callSuper = false)
@Table(name = "permit")
@Getter
@Setter
@ToString
public class PermitEntity extends BaseEntity {
    @Column(length = 20)
    private String serialNumber;

    @OneToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @Column(name = "start_word")
    private LocalDateTime startWork;

    @Column(name = "finish_word")
    private LocalDateTime finishWork;



}
