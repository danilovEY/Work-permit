package ru.kolaer.permit.component;

import org.springframework.stereotype.Component;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.PostEntity;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
@Component
public final class EmptyObjects {

    public static final PostEntity DEFAULT_POST = new PostEntity(-1, "default_post",
            "post", -1, "default_type", Collections.emptyList()){
        @Override
        public void setId(Integer id) {throw new UnsupportedOperationException();}
        @Override
        public void setName(String name) {throw new UnsupportedOperationException();}
        @Override
        public void setAbbreviatedName(String abbreviatedName) {throw new UnsupportedOperationException();}
        @Override
        public void setRang(Integer rang) {throw new UnsupportedOperationException();}
        @Override
        public void setTypeRang(String typeRang) {throw new UnsupportedOperationException();}
        @Override
        public void setEmployees(List<EmployeeEntity> employees) {throw new UnsupportedOperationException();}
    };

    public static final DepartmentEntity DEFAULT_DEPARTMENT = new DepartmentEntity(-1, "default_department",
            "department", Collections.emptyList()){
        @Override
        public void setId(Integer id) {throw new UnsupportedOperationException();}
        @Override
        public void setName(String name) {throw new UnsupportedOperationException();}
        @Override
        public void setAbbreviatedName(String abbreviatedName) {throw new UnsupportedOperationException();}
        @Override
        public void setEmployees(List<EmployeeEntity> employees) {throw new UnsupportedOperationException();}
    };

    public static final EmployeeEntity DEFAULT_EMPLOYEE = new EmployeeEntity(-1, "initials",
            new Date(0), -1, DEFAULT_DEPARTMENT, DEFAULT_POST){
        @Override
        public void setId(Integer id) {throw new UnsupportedOperationException();}
        @Override
        public void setInitials(String initials) {throw new UnsupportedOperationException();}
        @Override
        public void setBirthday(Date birthday) {throw new UnsupportedOperationException();}
        @Override
        public void setPersonalNumber(Integer personalNumber) {throw new UnsupportedOperationException();}
        @Override
        public void setDepartment(DepartmentEntity department) {throw new UnsupportedOperationException();}
        @Override
        public void setPost(PostEntity post) {throw new UnsupportedOperationException();}
    };

}
