package ru.kolaer.permit.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dto.NotificationContents;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.NotificationEntity;
import ru.kolaer.permit.service.EmployeePageService;
import ru.kolaer.permit.service.NotificationPageService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Danilov on 29.05.2017.
 */
public abstract class BaseController {

    final EmployeePageService employeePageService;
    final NotificationPageService notificationPageService;
    private final EmployeeEntity defaultEmployee;

    private final String adminName;

    public BaseController(String defaultLogin,
                          EmployeePageService employeePageService,
                          NotificationPageService notificationPageService) {
        this.employeePageService = employeePageService;
        this.adminName = defaultLogin;
        this.notificationPageService = notificationPageService;

        this.defaultEmployee = new EmployeeEntity();
        this.defaultEmployee.setId(-1L);
        this.defaultEmployee.setInitials(this.adminName);
        this.defaultEmployee.setUsername(this.adminName);
        this.defaultEmployee.setPassword("Смотри в конфигурационном файле");
        this.defaultEmployee.setPersonnelNumber(0L);
        this.defaultEmployee.setBirthday(new Date());
        this.defaultEmployee.setEmail("test@test.ru");
        this.defaultEmployee.setRemoved(true);
        this.defaultEmployee.setPost(EmptyObjects.DEFAULT_POST);
        this.defaultEmployee.setDepartment(EmptyObjects.DEFAULT_DEPARTMENT);

    }

    ModelAndView createDefaultView(String view) {
        final EmployeeEntity authEmp = this.getAuthEmployee();

        NotificationContents notificationContents = this.notificationPageService
                .getNotificationContents(authEmp.getId());


        final ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("notificationContents", notificationContents);
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
