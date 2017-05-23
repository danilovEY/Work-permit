package ru.kolaer.permit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Danilov on 23.05.2017.
 */
@Entity
@Table(name = "security_measures")
@Getter
@Setter
@NoArgsConstructor
public class SecurityMeasures extends BaseEntity {

    @Column
    private String nameElectrical;

    @Column
    private String typeAction;

}
