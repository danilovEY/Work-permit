package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.service.EmployeePageService;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeesController {

    private final EmployeePageService employeePageService;

    @Autowired
    public EmployeesController(EmployeePageService employeePageService) {
        this.employeePageService = employeePageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<EmployeeEntity> employeePage = this.employeePageService.getAll(number, pageSize);

        final ModelAndView page = new ModelAndView("employees");
        page.addObject("employeesPage", employeePage);
        return page;
    }

}
