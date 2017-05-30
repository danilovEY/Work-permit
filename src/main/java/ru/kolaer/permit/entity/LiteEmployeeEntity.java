package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by danilovey on 14.04.2017.
 */
@Entity
@Table(name = "employees")
@Setter
@Getter
@NoArgsConstructor
public class LiteEmployeeEntity extends BaseEntity {

    @Column(name = "initials", length = 100, nullable = false)
    private String initials;

    @Column(name = "personnel_number", length = 10,
            unique = true, nullable = false)
    private Integer personnelNumber;

}
