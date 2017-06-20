package ru.kolaer.permit.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.kolaer.permit.dto.NotificationType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by danilovey on 20.06.2017.
 */
@Table(name = "notification")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NotificationEntity extends BaseEntity {

    @Column(name = "read")
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
    private NotificationType type;

    @Column(name = "event_from_id", nullable = false)
    private Long eventFromId;

    @Column(name = "message", length = 140)
    private String message;

}
