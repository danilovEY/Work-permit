package ru.kolaer.permit.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Danilov on 24.05.2017.
 */
@EqualsAndHashCode(callSuper = false)
//@Entity
//@Table(name = "security_system")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Deprecated
public class SecuritySystem extends BaseEntity {



    @OneToOne(mappedBy = "securitySystem", fetch = FetchType.LAZY)
    @JoinColumn(name = "permit_id", nullable = false)
    private PermitEntity permit;



}
