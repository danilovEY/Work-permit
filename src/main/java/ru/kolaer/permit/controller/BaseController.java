package ru.kolaer.permit.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.service.EmployeePageService;

import java.util.Date;

/**
 * Created by Danilov on 29.05.2017.
 */
public abstract class BaseController {

    final EmployeePageService employeePageService;
    private final EmployeeEntity defaultEmployee;

    private final String adminName;

    public BaseController(String defaultLogin,
            EmployeePageService employeePageService) {
        this.employeePageService = employeePageService;
        this.adminName = defaultLogin;

        this.defaultEmployee = new EmployeeEntity();
        this.defaultEmployee.setId(-1);
        this.defaultEmployee.setInitials(this.adminName);
        this.defaultEmployee.setUsername(this.adminName);
        this.defaultEmployee.setPassword("Смотри в конфигурационном файле");
        this.defaultEmployee.setPersonnelNumber(-1);
        this.defaultEmployee.setBirthday(new Date());
        this.defaultEmployee.setEmail("test@test.ru");
        this.defaultEmployee.setRemoved(true);
        this.defaultEmployee.setPost(EmptyObjects.DEFAULT_POST);
        this.defaultEmployee.setDepartment(EmptyObjects.DEFAULT_DEPARTMENT);

    }

    ModelAndView createDefaultView(String view) {
        final EmployeeEntity authEmp = this.getAuthEmployee();

        final ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("authEmployee", authEmp);
        return modelAndView;
    }

    EmployeeEntity getAuthEmployee() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null)
            return EmptyObjects.DEFAULT_EMPLOYEE;

        final String username = auth.getName();

        return !adminName.equals(username)
                ? this.employeePageService.getByUsername(username)
                : this.defaultEmployee;
    }

}
