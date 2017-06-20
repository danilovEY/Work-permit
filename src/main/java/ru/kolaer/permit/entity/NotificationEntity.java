package ru.kolaer.permit.entity;

import lombok.*;
import ru.kolaer.permit.dto.NotificationType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by danilovey on 20.06.2017.
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class NotificationEntity extends BaseEntity {

    @Column(name = "read_notify", nullable = false)
    private boolean read;

    @Column(name = "to_id", nullable = false, updatable = false, insertable = false)
    private Long toId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id", nullable = false)
    private EmployeeEntity to;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "type", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "event_from_id", nullable = false)
    private Long eventFromId;

    @Column(name = "message", length = 140)
    private String message;

}
