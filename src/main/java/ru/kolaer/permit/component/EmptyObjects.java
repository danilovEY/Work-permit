package ru.kolaer.permit.component;

import ru.kolaer.permit.entity.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public final class EmptyObjects {

    public static final PostEntity DEFAULT_POST =
            new PostEntity(-1L, "", "", -1, "", Collections.emptyList()){
                @Override
                public void setId(Long id) {throw new UnsupportedOperationException("Post entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setName(String name) {throw new UnsupportedOperationException("Post entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setAbbreviatedName(String abbreviatedName) {throw new UnsupportedOperationException("Post entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setRang(Integer rang) {throw new UnsupportedOperationException("Post entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setTypeRang(String typeRang) {throw new UnsupportedOperationException("Post entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setEmployees(List<EmployeeEntity> employees) {throw new UnsupportedOperationException("Post entity object is empty! You can't write value to empty objects.");}
    };

    public static final DepartmentEntity DEFAULT_DEPARTMENT =
            new DepartmentEntity(-1L, "", "", Collections.emptyList()){
                @Override
                public void setId(Long id) {throw new UnsupportedOperationException("Department entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setName(String name) {throw new UnsupportedOperationException("Department entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setAbbreviatedName(String abbreviatedName) {throw new UnsupportedOperationException("Department entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setEmployees(List<EmployeeEntity> employees) {throw new UnsupportedOperationException("Department entity object is empty! You can't write value to empty objects.");}
    };

    public static final EmployeeEntity DEFAULT_EMPLOYEE =
            new EmployeeEntity(-1L, "", new Date(0), 0L, "", "", "", "", "", DEFAULT_DEPARTMENT, DEFAULT_POST){
                @Override
                public void setId(Long id) {throw new UnsupportedOperationException("Employee entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setInitials(String initials) {throw new UnsupportedOperationException("Employee entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setBirthday(Date birthday) {throw new UnsupportedOperationException("Employee entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setPersonnelNumber(Long personnelNumber) {throw new UnsupportedOperationException("Employee entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setDepartment(DepartmentEntity department) {throw new UnsupportedOperationException("Employee entity object is empty! You can't write value to empty objects.");}
                @Override
                public void setPost(PostEntity post) {throw new UnsupportedOperationException("Employee entity object is empty! You can't write value to empty objects.");}
    };

    public static final RoleEntity DEFAULT_ROLE = new RoleEntity(-1L, DEFAULT_EMPLOYEE, "ROLE_USER") {
        @Override
        public void setId(Long id) {throw new UnsupportedOperationException("Role entity object is empty! You can't write value to empty objects.");}
        @Override
        public void setRole(String role) {throw new UnsupportedOperationException("Role entity object is empty! You can't write value to empty objects.");}
        @Override
        public void setEmployee(EmployeeEntity employee) {throw new UnsupportedOperationException("Role entity object is empty! You can't write value to empty objects.");}
    };

    public static final RoleEntity DEFAULT_FULL_ROLE = new RoleEntity(-1L, DEFAULT_EMPLOYEE, "") {
        @Override
        public void setId(Long id) {throw new UnsupportedOperationException("Full Role entity object is empty! You can't write value to empty objects.");}
        @Override
        public void setRole(String role) {throw new UnsupportedOperationException("Full Role entity object is empty! You can't write value to empty objects.");}
        @Override
        public void setEmployee(EmployeeEntity employee) {throw new UnsupportedOperationException("Full Role entity object is empty! You can't write value to empty objects.");}
    };

    public static final PermitEntity DEFAULT_PERMIT = new PermitEntity();
    public static final PermitStatusHistoryEntity DEFAULT_STATUS_HISTORY = new PermitStatusHistoryEntity();
    public static final WorkEvent DEFAULT_WORK_EVENT = new WorkEvent();
}
