package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.service.DepartmentPageService;

import java.util.Collections;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/departments")
@Slf4j
public class DepartmentsController {

    private final DepartmentPageService departmentPageService;

    @Autowired
    public DepartmentsController(DepartmentPageService departmentPageService) {
        this.departmentPageService = departmentPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<DepartmentEntity> departmentPage = this.departmentPageService.getAll(number, pageSize);

        final ModelAndView modelAndView = new ModelAndView("departments");
        modelAndView.addObject("departmentPage", departmentPage);
        modelAndView.addObject("department", new DepartmentEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addDepartment(DepartmentEntity departmentEntity) {
        departmentEntity.setId(null);
        departmentEntity.setEmployees(Collections.emptyList());

        this.departmentPageService.add(departmentEntity);

        return "redirect:/departments";
    }

}
