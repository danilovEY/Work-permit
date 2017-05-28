package ru.kolaer.permit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Danilov on 27.05.2017.
 */
@Entity
@Table(name = "permit_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermitStatusHistoryEntity extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPermit status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    /**Дата статуса*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date statusDate;

    @Column(name = "permit_id", insertable=false, updatable=false)
    private Integer permitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permit_id")
    private PermitEntity permit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermitStatusHistoryEntity that = (PermitStatusHistoryEntity) o;

        if (status != that.status) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (statusDate != null ? !statusDate.equals(that.statusDate) : that.statusDate != null) return false;
        return permitId != null ? permitId.equals(that.permitId) : that.permitId == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (statusDate != null ? statusDate.hashCode() : 0);
        result = 31 * result + (permitId != null ? permitId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PermitStatusHistoryEntity{" +
                "status=" + status +
                ", employee=" + employee +
                ", statusDate=" + statusDate +
                ", permitId=" + permitId +
                '}';
    }
}
