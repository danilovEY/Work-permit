package ru.kolaer.permit.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by danilovey on 04.05.2017.
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "permit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventPermitEntity extends BaseEntity {
    /**Мероприятия*/
    @OneToMany(mappedBy = "permit", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<WorkEvent> workEvents;

}
